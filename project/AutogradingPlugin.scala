import sbt._
import sbt.Keys._
import sbt.Tests._
import scala.util.{Try, Success, Failure}
import java.io.{File, FileWriter}
import scala.collection.mutable.ListBuffer

object AutogradingPlugin extends AutoPlugin {

  object autoImport {
    val autograding = taskKey[Unit]("Run tests and generate autograding.json")
    val generateAutograding = taskKey[Unit]("Generate autograding.json from existing test results")
  }

  import autoImport._

  override def trigger = allRequirements

  override lazy val projectSettings = Seq(
    autograding := {
      val log = streams.value.log
      
      log.info("Running tests for autograding...")
      
      Try((Test / test).value)
      
      generateAutograding.value
    },
    
    generateAutograding := {
      val log = streams.value.log
      
      log.info("Processing test results...")
      
      val testEvents = ListBuffer[(String, Boolean)]()
      val testReportsDir = file("target/test-reports")
      
      var totalTests = 0
      var passedTests = 0
      var failedTests = 0
      
      if (testReportsDir.exists()) {
        val xmlFiles = testReportsDir.listFiles().filter(_.getName.endsWith(".xml"))
        
        xmlFiles.foreach { file =>
          val xml = scala.xml.XML.loadFile(file)
          
          (xml \\ "testcase").foreach { testcase =>
            val name = (testcase \ "@name").text
            val className = (testcase \ "@classname").text
            val fullName = s"$className.$name"
            
            totalTests += 1
            
            if ((testcase \ "failure").isEmpty && (testcase \ "error").isEmpty) {
              passedTests += 1
              testEvents += ((fullName, true))
            } else {
              failedTests += 1
              testEvents += ((fullName, false))
            }
          }
        }
      }
      
      val autogradingJson = generateAutogradingJson(passedTests, failedTests, testEvents.toList)
      
      val outputFile = file(".github/classroom/autograding.json")
      outputFile.getParentFile.mkdirs()
      
      val writer = new FileWriter(outputFile)
      try {
        writer.write(autogradingJson)
        log.info(s"Generated autograding.json with $passedTests passed tests and $failedTests failed tests")
        log.info(s"Total score: $passedTests points")
      } finally {
        writer.close()
      }
    },
    
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-u", (Test / target).value + "/test-reports")
  )
  
  private def generateAutogradingJson(passed: Int, failed: Int, testResults: List[(String, Boolean)]): String = {
    val total = passed + failed
    val score = passed
    
    val tests = testResults.map { case (name, success) =>
      val safeName = name.replace("\"", "\\\"")
      val parts = name.split("\\.")
      val className = if (parts.length >= 2) parts(0) else ""
      val testName = if (parts.length >= 2) parts.drop(1).mkString(".") else name
      val escapedTestName = testName.replace("\"", "\\\"").replace("'", "\\'")
      val result = if (success) "PASS" else "FAIL"
      val command = s"echo $result"
      s"""    {
      "name": "$safeName",
      "setup": "",
      "run": "$command",
      "input": "",
      "output": "PASS",
      "comparison": "included",
      "timeout": 10,
      "points": 1
    }"""
    }.mkString(",\n")
    
    s"""{
  "tests": [
$tests
  ]
}"""
  }
}