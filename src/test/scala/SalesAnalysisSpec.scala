import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SalesAnalysisSpec extends AnyFlatSpec with Matchers {

  import SalesAnalysis._
  import SalesAnalysis.{laptop, mouse, keyboard}

  "allProductNames" should "return all product names" in {
    val result = allProductNames(products)
    result should contain theSameElementsAs List(
      "Laptop", "Mouse", "Keyboard", "Desk", "Chair", "Monitor", "Lamp"
    )
  }

  "expensiveProducts" should "return products costing more than 100€" in {
    val result = expensiveProducts(products)
    result.map(_.name) should contain theSameElementsAs List(
      "Laptop", "Desk", "Chair", "Monitor"
    )
  }

  "totalSalesCount" should "return the total number of sales" in {
    totalSalesCount(sales) shouldEqual 10
  }

  "totalQuantitySold" should "return the sum of all quantities" in {
    totalQuantitySold(sales) shouldEqual 24
  }

  "totalRevenue" should "calculate the total revenue from all sales" in {
    val expectedRevenue = 
      2 * 999.99 + // Sale 1: 2 Laptops
      5 * 29.99 +  // Sale 2: 5 Mice
      1 * 999.99 + // Sale 3: 1 Laptop
      3 * 299.99 + // Sale 4: 3 Desks
      2 * 79.99 +  // Sale 5: 2 Keyboards
      4 * 199.99 + // Sale 6: 4 Chairs
      3 * 29.99 +  // Sale 7: 3 Mice
      1 * 399.99 + // Sale 8: 1 Monitor
      1 * 999.99 + // Sale 9: 1 Laptop
      2 * 49.99    // Sale 10: 2 Lamps
    
    totalRevenue(sales) shouldEqual expectedRevenue +- 0.01
  }

  "uniqueCities" should "return all unique cities" in {
    uniqueCities(sales) should contain theSameElementsAs Set(
      "Paris", "Lyon", "Marseille", "Bordeaux"
    )
  }

  "electronicProductsSold" should "return electronic products that were sold" in {
    val result = electronicProductsSold(sales)
    result.map(_.name) should contain theSameElementsAs Set(
      "Laptop", "Mouse", "Keyboard", "Monitor"
    )
  }

  "salesInParis" should "return all sales made in Paris" in {
    val result = salesInParis(sales)
    result should have size 5
    result.foreach(_.customerCity shouldEqual "Paris")
  }

  "averagePriceOfSoldProducts" should "calculate the average price of products that were sold" in {
    // Products sold: Laptop, Mouse, Keyboard, Desk, Chair, Monitor, Lamp
    val result = averagePriceOfSoldProducts(sales)
    val expectedAverage = (999.99 + 29.99 + 79.99 + 299.99 + 199.99 + 399.99 + 49.99) / 7.0
    result shouldEqual expectedAverage +- 0.01
  }

  "productsByPriceDesc" should "return products sorted by price descending" in {
    val result = productsByPriceDesc(products)
    result.head.name shouldEqual "Laptop"
    result.last.name shouldEqual "Mouse"
  }

  "largeOrders" should "return orders with quantity >= 3" in {
    val result = largeOrders(sales)
    result should have size 4
    result.foreach(_.quantity should be >= 3)
  }

  "revenueByProduct" should "calculate revenue per product" in {
    val result = revenueByProduct(sales)
    
    // Laptop: 2*999.99 + 1*999.99 + 1*999.99 = 4*999.99
    result(laptop) shouldEqual 4 * 999.99 +- 0.01
    
    // Mouse: 5*29.99 + 3*29.99 = 8*29.99
    result(mouse) shouldEqual 8 * 29.99 +- 0.01
    
    // Keyboard: 2*79.99
    result(keyboard) shouldEqual 2 * 79.99 +- 0.01
  }

  "top3ProductsByRevenue" should "return top 3 products by revenue" in {
    val result = top3ProductsByRevenue(sales)
    result should have size 3
    
    // Vérifier l'ordre décroissant
    result(0)._2 should be >= result(1)._2
    result(1)._2 should be >= result(2)._2
    
    // Le laptop devrait être en première position
    result(0)._1 shouldEqual laptop
  }

  "salesByCity" should "group sales by city" in {
    val result = salesByCity(sales)
    result("Paris") should have size 5
    result("Lyon") should have size 3
    result("Marseille") should have size 1
    result("Bordeaux") should have size 1
  }

  "cityStats" should "calculate count and revenue per city" in {
    val result = cityStats(sales)
    
    // Paris: 5 ventes
    result("Paris")._1 shouldEqual 5
    
    // Lyon: 3 ventes
    result("Lyon")._1 shouldEqual 3
    
    // Vérifier que le revenu est positif pour chaque ville
    result.values.foreach { case (count, revenue) =>
      count should be > 0
      revenue should be > 0.0
    }
  }

  "productsByCategory" should "group products by category" in {
    val result = productsByCategory(products)
    result("Electronics") should have size 4
    result("Furniture") should have size 3
  }

  "salesAnalysisByCategory" should "calculate quantity and revenue per category" in {
    val result = salesAnalysisByCategory(sales)
    
    result should contain key "Electronics"
    result should contain key "Furniture"
    
    // Vérifier que les valeurs sont positives
    result.values.foreach { case (quantity, revenue) =>
      quantity should be > 0
      revenue should be > 0.0
    }
  }

  "top3CitiesByRevenue" should "return top 3 cities by revenue in descending order" in {
    val result = top3CitiesByRevenue(sales)
    result should have size 3
    
    // Vérifier l'ordre décroissant
    result(0)._2 should be >= result(1)._2
    result(1)._2 should be >= result(2)._2
    
    // Toutes les villes doivent avoir un revenu positif
    result.foreach { case (city, revenue) =>
      city should not be empty
      revenue should be > 0.0
    }
  }

  "averageOrderValueByCity" should "calculate average order value per city" in {
    val result = averageOrderValueByCity(sales)
    
    result should contain key "Paris"
    result should contain key "Lyon" 
    result should contain key "Marseille"
    result should contain key "Bordeaux"
    
    // Toutes les valeurs moyennes doivent être positives
    result.values.foreach(_ should be > 0.0)
  }

  "mostSoldProduct" should "return the product with highest total quantity sold" in {
    val result = mostSoldProduct(sales)
    result should be(defined)
    
    val (product, quantity) = result.get
    quantity should be > 0
    
    // Mouse est vendu en quantité 5+3=8, Laptop en 2+1+1=4
    // Mouse devrait être le plus vendu
    product shouldEqual mouse
    quantity shouldEqual 8
  }
}