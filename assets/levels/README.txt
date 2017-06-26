The level.txt files are read as follows:

  The keywords GUARDS, CAMERAGUARDS, COMPUTERS, DOOR, HACKER and MAP divide the text into data chunks. What is left between keywords is data of an element of the collection that the keyword in the right represents. For all keywords but the MAP, this data is Integers and they represent the following values (by order of appearance):
  
    in GUARDS: Direction, velocity, sets of (x,y) values called instructions (they define the guard's path).
    in CAMERAGUARDS: Direction, velocity, sets of x values called instructions (they define the camera's rotation).
    in COMPUTERS: Direction, consecutiveHacks (time it takes to hack it)
    in DOORS: Direction
    in HACKER: Direction, velocity
    in MAP: there is a 17x23 matrix represented. "/\n" separates each row, "," separated the elements in each row.
            Each keyword declares that an object of that kind must be put in that (x,y) position, with 0 representing the floor.
