// ============================================
// EXERCICE : ANALYSE DE DONNÉES DE VENTE
// ============================================
// 
// Contexte : Vous travaillez pour une entreprise de e-commerce.
// Vous devez analyser les données de vente pour générer des rapports.
//
// Objectif : Compléter les fonctions en utilisant les opérations
// sur les collections Scala (map, filter, foldLeft, groupBy, etc.)
// Les exercices sont classés par difficulté croissante.
// ============================================

case class Product(id: Int, name: String, category: String, price: Double)

case class Sale(
  orderId: Int,
  productId: Int,
  quantity: Int,
  customerCity: String,
  date: String  // Format: "2025-01-15"
)

object SalesAnalysis {
  
  // Données de test
  val products = List(
    Product(1, "Laptop", "Electronics", 999.99),
    Product(2, "Mouse", "Electronics", 29.99),
    Product(3, "Keyboard", "Electronics", 79.99),
    Product(4, "Desk", "Furniture", 299.99),
    Product(5, "Chair", "Furniture", 199.99),
    Product(6, "Monitor", "Electronics", 399.99),
    Product(7, "Lamp", "Furniture", 49.99)
  )
  
  val sales = List(
    Sale(1, 1, 2, "Paris", "2025-01-15"),
    Sale(2, 2, 5, "Lyon", "2025-01-15"),
    Sale(3, 1, 1, "Paris", "2025-01-16"),
    Sale(4, 4, 3, "Marseille", "2025-01-16"),
    Sale(5, 3, 2, "Paris", "2025-01-17"),
    Sale(6, 5, 4, "Lyon", "2025-01-17"),
    Sale(7, 2, 3, "Paris", "2025-01-18"),
    Sale(8, 6, 1, "Bordeaux", "2025-01-18"),
    Sale(9, 1, 1, "Lyon", "2025-01-19"),
    Sale(10, 7, 2, "Paris", "2025-01-19")
  )

  // ============================================
  // NIVEAU DÉBUTANT - Opérations simples
  // ============================================

  // EXERCICE 1 : Tous les noms de produits
  // Retourner une liste avec seulement les noms des produits
  // Opérations : map
  
  def allProductNames(products: List[Product]): List[String] = {
    ???
  }

  // EXERCICE 2 : Produits chers
  // Trouver tous les produits qui coûtent plus de 100€
  // Opérations : filter
  
  def expensiveProducts(products: List[Product]): List[Product] = {
    ???
  }

  // EXERCICE 3 : Nombre total de ventes
  // Calculer combien de ventes ont été réalisées au total
  // Opérations : size ou length
  
  def totalSalesCount(sales: List[Sale]): Int = {
    ???
  }

  // EXERCICE 4 : Quantité totale vendue
  // Calculer la somme de toutes les quantités vendues
  // Opérations : map + sum ou foldLeft
  
  def totalQuantitySold(sales: List[Sale]): Int = {
    ???
  }

  // EXERCICE 5 : Villes uniques
  // Trouver toutes les villes différentes où on a vendu
  // Opérations : map + toSet ou distinct
  
  def uniqueCities(sales: List[Sale]): Set[String] = {
    ???
  }

  // EXERCICE 6 : Produits d'électronique
  // Trouver tous les noms de produits dans la catégorie "Electronics"
  // Opérations : filter + map
  
  def electronicProductNames(products: List[Product]): List[String] = {
    ???
  }

  // EXERCICE 7 : Ventes à Paris
  // Trouver toutes les ventes effectuées à Paris
  // Opérations : filter
  
  def salesInParis(sales: List[Sale]): List[Sale] = {
    ???
  }

  // EXERCICE 8 : Prix moyen des produits
  // Calculer le prix moyen de tous les produits
  // Opérations : map + sum, ou foldLeft
  
  def averageProductPrice(products: List[Product]): Double = {
    ???
  }

  // ============================================
  // NIVEAU INTERMÉDIAIRE - Combinaisons et jointures
  // ============================================

  // EXERCICE 9 : Trouver un produit par ID
  // Créer une fonction helper pour trouver un produit par son ID
  // Opérations : find
  
  def findProductById(products: List[Product], id: Int): Option[Product] = {
    ???
  }

  // EXERCICE 10 : Prix total d'une vente
  // Calculer le prix total (quantité × prix) pour une vente donnée
  // Vous devrez trouver le produit correspondant
  // Opérations : find, puis calcul
  
  def saleTotalPrice(sale: Sale, products: List[Product]): Double = {
    ???
  }

  // EXERCICE 11 : Chiffre d'affaires total
  // Calculer le revenu total de toutes les ventes
  // Opérations : map (avec saleTotalPrice) + sum, ou foldLeft
  
  def totalRevenue(sales: List[Sale], products: List[Product]): Double = {
    ???
  }

  // EXERCICE 12 : Produits triés par prix
  // Retourner les produits triés du plus cher au moins cher
  // Opérations : sortBy ou sortWith
  
  def productsByPriceDesc(products: List[Product]): List[Product] = {
    ???
  }

  // EXERCICE 13 : Top 3 des produits les plus chers
  // Retourner les 3 produits les plus chers
  // Opérations : sortBy + take
  
  def top3MostExpensive(products: List[Product]): List[Product] = {
    ???
  }

  // EXERCICE 14 : Ventes avec quantité importante
  // Trouver toutes les ventes où la quantité est >= 3
  // et retourner les IDs de commande
  // Opérations : filter + map
  
  def largeOrderIds(sales: List[Sale]): List[Int] = {
    ???
  }

  // ============================================
  // NIVEAU AVANCÉ - groupBy et agrégations
  // ============================================

  // EXERCICE 15 : Ventes par ville
  // Grouper les ventes par ville
  // Opérations : groupBy
  // Retour : Map[String, List[Sale]]
  
  def salesByCity(sales: List[Sale]): Map[String, List[Sale]] = {
    ???
  }

  // EXERCICE 16 : Nombre de ventes par ville
  // Pour chaque ville, compter combien de ventes ont été faites
  // Opérations : groupBy + mapValues (ou view.mapValues.toMap)
  // Retour : Map[String, Int]
  
  def salesCountByCity(sales: List[Sale]): Map[String, Int] = {
    ???
  }

  // EXERCICE 17 : Produits par catégorie
  // Grouper les produits par catégorie
  // Opérations : groupBy
  
  def productsByCategory(products: List[Product]): Map[String, List[Product]] = {
    ???
  }

  // EXERCICE 18 : Prix total par catégorie
  // Pour chaque catégorie, calculer la somme des prix de tous les produits
  // Opérations : groupBy + map avec sum
  
  def totalPriceByCategory(products: List[Product]): Map[String, Double] = {
    ???
  }

  // EXERCICE 19 : Revenu par ville
  // Calculer le revenu total généré dans chaque ville
  // Opérations : groupBy + map avec calcul du revenu
  
  def revenueByCity(sales: List[Sale], products: List[Product]): Map[String, Double] = {
    ???
  }

  // EXERCICE 20 : Top 3 des villes par chiffre d'affaires
  // Trouver les 3 villes qui génèrent le plus de revenu
  // Opérations : revenueByCity + toList + sortBy + take
  
  def top3CitiesByRevenue(sales: List[Sale], products: List[Product]): List[(String, Double)] = {
    ???
  }

  // ============================================
  // TESTS manuels
  // ============================================
  
  def main(args: Array[String]): Unit = {
    println("=== Tests des Exercices ===\n")
    
    println("--- NIVEAU DÉBUTANT ---\n")
    
    // Test Exercice 1
    println("Exercice 1 - Noms de produits :")
    try {
      println(s"  ${allProductNames(products).mkString(", ")}")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 2
    println("Exercice 2 - Produits > 100€ :")
    try {
      expensiveProducts(products).foreach(p => println(s"  ${p.name}: ${p.price}€"))
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 3
    println("Exercice 3 - Nombre total de ventes :")
    try {
      println(s"  ${totalSalesCount(sales)} ventes")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 4
    println("Exercice 4 - Quantité totale vendue :")
    try {
      println(s"  ${totalQuantitySold(sales)} unités")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 5
    println("Exercice 5 - Villes uniques :")
    try {
      println(s"  ${uniqueCities(sales).mkString(", ")}")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 6
    println("Exercice 6 - Produits électroniques :")
    try {
      println(s"  ${electronicProductNames(products).mkString(", ")}")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 7
    println("Exercice 7 - Ventes à Paris :")
    try {
      println(s"  ${salesInParis(sales).size} ventes")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 8
    println("Exercice 8 - Prix moyen :")
    try {
      println(f"  ${averageProductPrice(products)}%.2f€")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    println("--- NIVEAU INTERMÉDIAIRE ---\n")
    
    // Test Exercice 9
    println("Exercice 9 - Trouver produit ID 3 :")
    try {
      println(s"  ${findProductById(products, 3)}")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 10
    println("Exercice 10 - Prix de la première vente :")
    try {
      println(f"  ${saleTotalPrice(sales.head, products)}%.2f€")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 11
    println("Exercice 11 - Chiffre d'affaires total :")
    try {
      println(f"  ${totalRevenue(sales, products)}%.2f€")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 12
    println("Exercice 12 - Produits triés par prix (top 3) :")
    try {
      productsByPriceDesc(products).take(3).foreach(p => println(f"  ${p.name}: ${p.price}%.2f€"))
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 13
    println("Exercice 13 - Top 3 produits les plus chers :")
    try {
      top3MostExpensive(products).foreach(p => println(f"  ${p.name}: ${p.price}%.2f€"))
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 14
    println("Exercice 14 - Commandes avec quantité >= 3 :")
    try {
      println(s"  IDs: ${largeOrderIds(sales).mkString(", ")}")
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    println("--- NIVEAU AVANCÉ ---\n")
    
    // Test Exercice 15
    println("Exercice 15 - Ventes par ville (nombre par ville) :")
    try {
      salesByCity(sales).foreach { case (city, salesList) =>
        println(s"  $city: ${salesList.size} ventes")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 16
    println("Exercice 16 - Nombre de ventes par ville :")
    try {
      salesCountByCity(sales).foreach { case (city, count) =>
        println(s"  $city: $count ventes")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 17
    println("Exercice 17 - Produits par catégorie :")
    try {
      productsByCategory(products).foreach { case (category, prods) =>
        println(s"  $category: ${prods.map(_.name).mkString(", ")}")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 18
    println("Exercice 18 - Prix total par catégorie :")
    try {
      totalPriceByCategory(products).foreach { case (category, total) =>
        println(f"  $category: $total%.2f€")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 19
    println("Exercice 19 - Revenu par ville :")
    try {
      revenueByCity(sales, products).foreach { case (city, revenue) =>
        println(f"  $city: $revenue%.2f€")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
    
    // Test Exercice 20
    println("Exercice 20 - Top 3 des villes par revenu :")
    try {
      top3CitiesByRevenue(sales, products).foreach { case (city, revenue) =>
        println(f"  $city: $revenue%.2f€")
      }
    } catch {
      case _: NotImplementedError => println("  ⚠ Non implémenté")
    }
    println()
  }
}

