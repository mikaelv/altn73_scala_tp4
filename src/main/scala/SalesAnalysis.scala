// ============================================
// EXERCICE : ANALYSE DE DONNÉES DE VENTE - VERSION ORIENTÉE OBJET
// ============================================
//
// Contexte : Version améliorée avec une approche plus orientée objet.
// La classe Sale contient maintenant une référence directe au Product
// au lieu d'un simple ID, ce qui évite les jointures manuelles.
//
// Objectif : Compléter les fonctions en utilisant les opérations
// sur les collections Scala avec une approche plus fonctionnelle.
// ============================================

case class Product(name: String, category: String, price: Double):
  def isExpensive: Boolean = price > 100
  def isElectronic: Boolean = category == "Electronics"

case class Sale(
    orderId: Int,
    product: Product, // Référence directe au produit
    quantity: Int,
    customerCity: String,
    date: String
):
  def totalPrice: Double = quantity * product.price
  def isLargeOrder: Boolean = quantity >= 3
  def isFromCity(city: String): Boolean = customerCity == city

object SalesAnalysis:

  val laptop: Product = Product("Laptop", "Electronics", 999.99)
  val mouse: Product = Product("Mouse", "Electronics", 29.99)
  val keyboard: Product = Product("Keyboard", "Electronics", 79.99)
  val desk: Product = Product("Desk", "Furniture", 299.99)
  val chair: Product = Product("Chair", "Furniture", 199.99)
  val monitor: Product = Product("Monitor", "Electronics", 399.99)
  val lamp: Product = Product("Lamp", "Furniture", 49.99)

  val products: Seq[Product] = Seq(laptop, mouse, keyboard, desk, chair, monitor, lamp)

  val sales: Seq[Sale] = Seq(
    Sale(1, laptop, 2, "Paris", "2025-01-15"),
    Sale(2, mouse, 5, "Lyon", "2025-01-15"),
    Sale(3, laptop, 1, "Paris", "2025-01-16"),
    Sale(4, desk, 3, "Marseille", "2025-01-16"),
    Sale(5, keyboard, 2, "Paris", "2025-01-17"),
    Sale(6, chair, 4, "Lyon", "2025-01-17"),
    Sale(7, mouse, 3, "Paris", "2025-01-18"),
    Sale(8, monitor, 1, "Bordeaux", "2025-01-18"),
    Sale(9, laptop, 1, "Lyon", "2025-01-19"),
    Sale(10, lamp, 2, "Paris", "2025-01-19")
  )

  // ============================================
  // NIVEAU DÉBUTANT - Opérations simples
  // ============================================

  // EXERCICE 1 : Tous les noms de produits
  // Retourner une liste avec seulement les noms des produits
  // Astuce : Maintenant plus simple sans jointure !
  def allProductNames(products: Seq[Product]): Seq[String] =
    products.map(_.name)

  // EXERCICE 2 : Produits chers
  // Trouver tous les produits qui coûtent plus de 100€
  // Astuce : Utilisez la méthode isExpensive
  def expensiveProducts(products: Seq[Product]): Seq[Product] =
    products.filter(_.isExpensive)

  // EXERCICE 3 : Nombre total de ventes
  // Calculer combien de ventes ont été réalisées au total
  def totalSalesCount(sales: Seq[Sale]): Int =
    ???

  // EXERCICE 4 : Quantité totale vendue
  // Calculer la somme de toutes les quantités vendues
  def totalQuantitySold(sales: Seq[Sale]): Int =
    ???

  // EXERCICE 5 : Chiffre d'affaires total
  // Calculer le revenu total de toutes les ventes
  // Astuce : Utilisez la méthode totalPrice de Sale
  def totalRevenue(sales: Seq[Sale]): Double =
    ???

  // EXERCICE 6 : Villes uniques
  // Trouver toutes les villes différentes où on a vendu
  def uniqueCities(sales: Seq[Sale]): Set[String] =
    ???

  // EXERCICE 7 : Produits d'électronique vendus
  // Trouver tous les produits électroniques qui ont été vendus
  // Astuce : Utilisez la méthode isElectronic et évitez les doublons
  def electronicProductsSold(sales: Seq[Sale]): Set[Product] =
    ???

  // EXERCICE 8 : Ventes à Paris
  // Trouver toutes les ventes effectuées à Paris
  // Astuce : Utilisez la méthode isFromCity
  def salesInParis(sales: Seq[Sale]): Seq[Sale] =
    ???

  // ============================================
  // NIVEAU INTERMÉDIAIRE - Combinaisons et calculs
  // ============================================

  // EXERCICE 9 : Prix moyen des produits vendus
  // Calculer le prix moyen des produits qui ont été effectivement vendus
  // (pas tous les produits, seulement ceux vendus)
  def averagePriceOfSoldProducts(sales: Seq[Sale]): Double =
    ???

  // EXERCICE 10 : Produits triés par prix
  // Retourner les produits triés du plus cher au moins cher
  def productsByPriceDesc(products: Seq[Product]): Seq[Product] =
    ???

  // EXERCICE 11 : Grosses commandes
  // Trouver toutes les ventes avec une quantité >= 3
  // Astuce : Utilisez la méthode isLargeOrder
  def largeOrders(sales: Seq[Sale]): Seq[Sale] =
    ???

  // EXERCICE 12 : Revenus par produit
  // Calculer le revenu total généré par chaque produit
  // Retour : Map[Product, Double]

  def revenueByProduct(sales: Seq[Sale]): Map[Product, Double] =
    ???

  // EXERCICE 13 : Top 3 des produits les plus rentables
  // Trouver les 3 produits qui génèrent le plus de revenus

  def top3ProductsByRevenue(sales: Seq[Sale]): Seq[(Product, Double)] =
    ???

  // ============================================
  // NIVEAU AVANCÉ - groupBy et agrégations complexes
  // ============================================

  // EXERCICE 14 : Ventes par ville
  // Grouper les ventes par ville
  def salesByCity(sales: Seq[Sale]): Map[String, Seq[Sale]] =
    ???

  // EXERCICE 15 : Statistiques par ville
  // Pour chaque ville, calculer : nombre de ventes et revenu total
  // Retour : Map[String, (Int, Double)] où le tuple est (nbVentes, revenu)

  def cityStats(sales: Seq[Sale]): Map[String, (Int, Double)] =
    ???

  // EXERCICE 16 : Produits par catégorie
  // Grouper les produits par catégorie
  def productsByCategory(products: Seq[Product]): Map[String, Seq[Product]] =
    ???

  // EXERCICE 17 : Analyse des ventes par catégorie
  // Pour chaque catégorie, calculer le nombre d'unités vendues et le revenu
  // Retour : Map[String, (Int, Double)] où le tuple est (quantité, revenu)

  def salesAnalysisByCategory(sales: Seq[Sale]): Map[String, (Int, Double)] =
    ???

  // EXERCICE 18 : Top 3 des villes par chiffre d'affaires
  // Trouver les 3 villes qui génèrent le plus de revenu

  def top3CitiesByRevenue(sales: Seq[Sale]): Seq[(String, Double)] =
    ???

  // EXERCICE 19 : Panier moyen par ville
  // Calculer la valeur moyenne d'une commande dans chaque ville
  // Astuce : Revenu total / Nombre de ventes

  def averageOrderValueByCity(sales: Seq[Sale]): Map[String, Double] =
    ???

  // EXERCICE 20 : Produit le plus vendu (en quantité)
  // Trouver le produit qui a été vendu en plus grande quantité
  def mostSoldProduct(sales: Seq[Sale]): Option[(Product, Int)] =
    ???
