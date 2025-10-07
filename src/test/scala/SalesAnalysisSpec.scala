import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SalesAnalysisSpec extends AnyFlatSpec with Matchers {

  import SalesAnalysis._

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

  "uniqueCities" should "return all unique cities" in {
    uniqueCities(sales) should contain theSameElementsAs Set(
      "Paris", "Lyon", "Marseille", "Bordeaux"
    )
  }

  "electronicProductNames" should "return names of electronic products" in {
    val result = electronicProductNames(products)
    result should contain theSameElementsAs List(
      "Laptop", "Mouse", "Keyboard", "Monitor"
    )
  }

  "salesInParis" should "return all sales made in Paris" in {
    val result = salesInParis(sales)
    result should have size 5
    result.foreach(_.customerCity shouldEqual "Paris")
  }

  "averageProductPrice" should "calculate the average price of all products" in {
    val result = averageProductPrice(products)
    result shouldEqual (999.99 + 29.99 + 79.99 + 299.99 + 199.99 + 399.99 + 49.99) / 7.0 +- 0.01
  }

  "findProductById" should "find a product by its ID" in {
    findProductById(products, 3) shouldEqual Some(Product(3, "Keyboard", "Electronics", 79.99))
    findProductById(products, 99) shouldEqual None
  }

  "saleTotalPrice" should "calculate the total price for a sale" in {
    val sale = Sale(1, 1, 2, "Paris", "2025-01-15") // 2 Laptops at 999.99€
    saleTotalPrice(sale, products) shouldEqual 1999.98 +- 0.01
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
    
    totalRevenue(sales, products) shouldEqual expectedRevenue +- 0.01
  }

  "productsByPriceDesc" should "return products sorted by price descending" in {
    val result = productsByPriceDesc(products)
    result.head.name shouldEqual "Laptop"
    result.last.name shouldEqual "Mouse"
  }

  "top3MostExpensive" should "return the 3 most expensive products" in {
    val result = top3MostExpensive(products)
    result should have size 3
    result.map(_.name) shouldEqual List("Laptop", "Monitor", "Desk")
  }

  "largeOrderIds" should "return order IDs with quantity >= 3" in {
    val result = largeOrderIds(sales)
    result should contain theSameElementsAs List(2, 4, 6, 7)
  }

  "salesByCity" should "group sales by city" in {
    val result = salesByCity(sales)
    result("Paris") should have size 5
    result("Lyon") should have size 3
    result("Marseille") should have size 1
    result("Bordeaux") should have size 1
  }

  "salesCountByCity" should "count sales per city" in {
    val result = salesCountByCity(sales)
    result("Paris") shouldEqual 5
    result("Lyon") shouldEqual 3
    result("Marseille") shouldEqual 1
    result("Bordeaux") shouldEqual 1
  }

  "productsByCategory" should "group products by category" in {
    val result = productsByCategory(products)
    result("Electronics") should have size 4
    result("Furniture") should have size 3
  }

  "totalPriceByCategory" should "calculate total price per category" in {
    val result = totalPriceByCategory(products)
    val electronicsTotal = 999.99 + 29.99 + 79.99 + 399.99
    val furnitureTotal = 299.99 + 199.99 + 49.99
    
    result("Electronics") shouldEqual electronicsTotal +- 0.01
    result("Furniture") shouldEqual furnitureTotal +- 0.01
  }

  "revenueByCity" should "calculate revenue per city" in {
    val result = revenueByCity(sales, products)
    
    // Calculs attendus:
    // Paris: Sale(1,1,2) + Sale(3,1,1) + Sale(5,3,2) + Sale(7,2,3) + Sale(10,7,2)
    //        = 2*999.99 + 1*999.99 + 2*79.99 + 3*29.99 + 2*49.99
    val parisRevenue = 2 * 999.99 + 1 * 999.99 + 2 * 79.99 + 3 * 29.99 + 2 * 49.99
    
    // Lyon: Sale(2,2,5) + Sale(6,5,4) + Sale(9,1,1)
    //       = 5*29.99 + 4*199.99 + 1*999.99
    val lyonRevenue = 5 * 29.99 + 4 * 199.99 + 1 * 999.99
    
    // Marseille: Sale(4,4,3) = 3*299.99
    val marseilleRevenue = 3 * 299.99
    
    // Bordeaux: Sale(8,6,1) = 1*399.99
    val bordeauxRevenue = 1 * 399.99
    
    result("Paris") shouldEqual parisRevenue +- 0.01
    result("Lyon") shouldEqual lyonRevenue +- 0.01
    result("Marseille") shouldEqual marseilleRevenue +- 0.01
    result("Bordeaux") shouldEqual bordeauxRevenue +- 0.01
  }

  "top3CitiesByRevenue" should "return top 3 cities by revenue in descending order" in {
    val result = top3CitiesByRevenue(sales, products)
    result should have size 3
    
    // Calcul des revenus attendus
    val parisRevenue = 2 * 999.99 + 1 * 999.99 + 2 * 79.99 + 3 * 29.99 + 2 * 49.99
    val lyonRevenue = 5 * 29.99 + 4 * 199.99 + 1 * 999.99
    val marseilleRevenue = 3 * 299.99
    
    // Vérification de l'ordre décroissant
    result(0)._2 should be >= result(1)._2
    result(1)._2 should be >= result(2)._2
    
    // Vérification des valeurs spécifiques si Paris est en tête
    if (result(0)._1 == "Paris") {
      result(0)._2 shouldEqual parisRevenue +- 0.01
    }
  }
}