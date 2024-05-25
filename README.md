# Recipe Rumble: A Text Adventure
Java based text-adventure game

javac -d bin src/main/java/com/recipeRumble/*.java src/main/java/com/recipeRumble/game/*.java src/main/java/com/recipeRumble/game/locations/*.java src/main/java/com/recipeRumble/game/foods/ingredients/*.java src/main/java/com/recipeRumble/game/utils/*.java

java -cp bin com.recipeRumble.Main 

TODO:
- FIX: you can indefinitely grab an item and add it to inventory
- inventory should do a check first if it is full
- add search method to location to location and override it in subclasses

COOKING CONCEPT:
- one ingredient is the main star
- one cooking main cooking method:
  - boiling
  - pan-frying
  - baking
- kitchen level 1 seasonings:
  - sea salt
  - sugar

TODO:
-  create fooditems for basic base ingredients:
  - rice/grains, noodles,
  - sea salt
  - sugar

23/05/24:
-fixed removeitem bug for inventory
- can now create a fooddish and add it to inventory

TODO:
- reinvent game objective:
  - you have one day to impress *insert name* by serving them breakfast lunch and dinner (3 tries)
- add fooditems to mountains
- allow to check inventory when deciding main ingredient OR show available options


TODOOO
- finish off serving functionality 