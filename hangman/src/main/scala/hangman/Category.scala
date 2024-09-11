package hangman

enum Category(val words: List[(String, String)]):
  case Animals extends Category(List(
    ("cat", "A pet that loves to catch mice"),
    ("dog", "A faithful friend of man"),
    ("bird", "An animal that can fly"),
    ("fish", "An animal that lives in water"),
    ("horse", "A large animal used for riding"),
    ("elephant", "The largest land animal"),
    ("lion", "King of beasts")
  ))

  case Fruits extends Category(List(
    ("apple", "A red fruit that grows on a tree"),
    ("banana", "A yellow fruit that grows on a palm tree"),
    ("orange", "Orange fruit that is rich in vitamin C"),
    ("grape", "A small purple fruit that grows on a vine"),
    ("strawberry", "Red fruit with small seeds"),
    ("pineapple", "Yellow fruit with thorns")
  ))

  case Countries extends Category(List(
    ("russia", "The largest country in the world"),
    ("usa", "The country consisting of 50 states"),
    ("china", "The most populated country in the world"),
    ("japan", "The country located on the Pacific Ocean islands")
  ))

  case Cities extends Category(List(
    ("moscow", "The capital of Russia"),
    ("london", "The capital of Great Britain"),
    ("paris", "The capital of France"),
    ("tokyo", "The capital of Japan")
  ))

  case AnimalsHard extends Category(List(
    ("antelope", "An animal similar to a deer but with long thin legs"),
    ("armadillo", "An animal covered with a shell"),
    ("beaver", "Dam-building rodent"),
    ("porcupine", "Large rodent with defensive spines or quills on the body and tail")
  ))

  case FruitsHard extends Category(List(
    ("apricot", "Yellow fruit with a stone"),
    ("avocado", "Green fruit with a large seed"),
    ("blackberry", "Black fruit with small seeds"),
    ("blueberry", "Blue fruit with small seeds")
  ))

  case CountriesHard extends Category(List(
    ("argentina", "A country in South America that is famous for its football"),
    ("belgium", "A country in Western Europe famous for its waffles"),
    ("colombia", "A country in South America that is famous for its coffee culture"),
    ("denmark", "The capital of this country is called the \"City of a Hundred Spires\"")
  ))

  case CitiesHard extends Category(List(
    ("amsterdam", "The capital city famous for its canals"),
    ("barcelona", "A city in Spain that is famous for its architecture"),
    ("brussels", "Capital of Belgium"),
    ("budapest", "Capital of Hungary")
  ))