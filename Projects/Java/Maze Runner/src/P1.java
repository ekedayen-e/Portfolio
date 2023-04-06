import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.Random;

//Represents a vertex
class Vertex {
  int id;
  int x;
  int y;
  Color color;
  ArrayList<Edge> outEdges; // edges from this node
  Vertex left;
  Vertex top;
  Vertex bot;
  Vertex right;
 
 Vertex(int x, int y, int id) {
   this.x = x;
   this.y = y;
   this.color = Color.GRAY;
   this.outEdges = new ArrayList<Edge>();
   this.id = id;
   this.left = null;
   this.top = null;
   this.bot = null;
   this.right = null;
   
 }
 
 //Draws a vertex
 WorldImage draw() {
   WorldImage cell = new RectangleImage(20, 20, OutlineMode.SOLID, this.color);
   WorldImage top = new LineImage(new Posn(20, 0), Color.BLACK).movePinhole(0, 10);
   WorldImage right = new LineImage(new Posn(0, 20), Color.BLACK).movePinhole(-10, 0);
   WorldImage left = new LineImage(new Posn(0, 20), Color.BLACK).movePinhole(10, 0);
   WorldImage bot = new LineImage(new Posn(20, 0), Color.BLACK).movePinhole(0, -10);
   WorldImage complete = new OverlayImage(new EmptyImage(), cell);
   
   if(this.left == null) {
     complete = new OverlayImage(left,complete);
   }
   if(this.right == null) {
     complete = new OverlayImage(right, complete);
   }
   if(this.top == null) {
     complete = new OverlayImage(top, complete);
   }
   if(this.bot == null) {
     complete = new OverlayImage(bot, complete);
   }
   return complete;
 }
 
}

//Represents an edge
class Edge {
  Vertex from;
  Vertex to;
  int weight;
  
  Edge() {
    this.from = null;
    this.to = null;
    this.weight = new Random().nextInt(100) + 1;;
  }
  
//  public WorldImage draw() {
//    WorldImage con = new LineImage(new Posn(0, 20), Color.BLACK);
//    WorldImage down = new RotateImage(con, 90);
//    WorldImage left = new RotateImage(con, 180);
//    WorldImage right = new RotateImage(con, 0);
//    
//    if(to.y > from.y) {
//      return down;
//    }
//    else if(to.y < from.y) {
//      return up;
//    }
//    else if(to.x > from.x) {
//      return right;
//    }
//    else {
//      return left;
//    }
//  }

  Edge(Vertex from, Vertex to) {
    this.from = from;
    this.to = to;
    this.weight = new Random().nextInt(100) + 1;
  }
  
}

//Represents a graph
class Graph {
  ArrayList<ArrayList<Vertex>> allVertices;
  
  Graph() {
    this.allVertices = new ArrayList<ArrayList<Vertex>>();
  }
  
}

class UnionFind {
  Graph g;
  HashMap<Integer, Integer> nodes;
  ArrayList<Edge> worklist;
  ArrayList<Edge> edgesInTree;
  
  UnionFind(Graph g) {
    this.g = g;
    this.nodes = new HashMap<Integer,Integer>();
    this.worklist = new ArrayList<Edge>();
    this.edgesInTree = new ArrayList<Edge>();
    this.initHash(g.allVertices);
    this.initWork(g.allVertices);
    Collections.sort(this.worklist, new EdgeComparator());
  }
  
  void initHash(ArrayList<ArrayList<Vertex>> alist) {
    for(ArrayList<Vertex> av: alist) {
      for(Vertex v : av) {
        this.nodes.put(v.id, v.id);
      }
    }
  }
  
  void initWork(ArrayList<ArrayList<Vertex>> alist) {
    for(ArrayList<Vertex> av: alist) {
      for(Vertex v : av) {
        for(Edge e: v.outEdges) {
          this.worklist.add(e);
        }
      }
    }
  }
  
  void findMST() {
    while(this.edgesInTree.size() < this.nodes.size() - 1) {
      int index = 0;
      Edge next = this.worklist.get(index);
      if(this.nodes.get(next.from.id) == this.nodes.get(next.to.id)) {
        index += 1;
      }
      else {
        this.edgesInTree.add(next);
        this.nodes.replace(next.from.id, next.to.id);
        index += 1;
      }
    }
  }
  
  
}

//Compares two edges 
class EdgeComparator implements Comparator<Edge> {
  
  @Override
  public int compare(Edge o1, Edge o2) {
    return o1.weight - o2.weight;
  }




  
}

class Utils {
  
  //Creates a maze for the game
  Graph makeMaze(int width, int height) {
    Graph graph = new Graph();
    int counter = 0;
    ArrayList<ArrayList<Vertex>> result;
    result = new ArrayList<ArrayList<Vertex>>();
    ArrayList <Vertex> temp;
    for(int i = 1; i <= height; i++) {
      temp = new ArrayList<Vertex>();
      for(int j = 1; j <= width; j++) {
        temp.add(new Vertex(i, j, counter));
        counter += 1;
      }
      result.add(temp);
    }
    graph.allVertices = result;
    return graph;
  }
  

  
}


class MazeRunner extends World {
  int width;
  int height;
  int currentTick;
  Graph maze;
  
  MazeRunner(int width, int height) {
    this.width = width;
    this.height = height;
    this.currentTick = 0;
    this.maze = new Utils().makeMaze(this.width, this.height);
    this.connect();

  }

  @Override
  public WorldScene makeScene() {
    WorldImage cols = new EmptyImage();
    for (int i = 0; i < this.height; i++) {
      WorldImage rows = new EmptyImage();
      for (int j = 0; j < this.width; j++) {
        Vertex current = this.maze.allVertices.get(i).get(j);
        rows = new BesideImage(rows, current.draw());
      }
      cols = new AboveImage(cols, rows);
    }
    WorldScene scene = new WorldScene(this.width * 30, this.height * 30);
    scene.placeImageXY(cols, this.width * 15, this.height * 15);
    return scene;
  }
  
  //Connects boxes in maze
  void connect() {
    for(int i = 0; i < this.height; i++) {
      for(int j = 0; j < this.width; j++) {
        
        Vertex current = this.maze.allVertices.get(i).get(j);
        
        if (j == 0 && i == 0) {
          Edge a = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          Edge b = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          current.outEdges.add(a);
          current.right = a.to;
          current.outEdges.add(b);
          current.bot = b.to;
          current.color = Color.GREEN;

        }
        if (j > 0 && j < this.width - 1 && i == 0) {
          Edge a = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          Edge b = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          Edge c = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          current.left = a.to;
          current.outEdges.add(b);
          current.right = b.to;
          current.outEdges.add(c);
          current.bot = c.to;

        }
        if (j == this.width - 1 && i == 0) {
          Edge a = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          Edge b = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          current.left = a.to;
          current.outEdges.add(b);
          current.bot = b.to;

        }
        if (j == 0 && i > 0 && i < this.height - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          Edge b = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge c = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          current.outEdges.add(a);
          current.right = a.to;
          current.top = b.to;
          current.outEdges.add(c);
          current.bot = c.to;

        }
        if (i > 0 && i < this.height - 1 && j > 0 && j < this.width - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge b = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          Edge c = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          Edge d = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          current.top = a.to;
          current.outEdges.add(b);
          current.right = b.to;
          current.outEdges.add(c);
          current.bot = c.to;
          current.left = d.to;

        }
        if (j == this.width - 1 && i > 0 && i < this.height - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge b = new Edge(current, this.maze.allVertices.get(i+1).get(j));
          Edge c = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          current.top = a.to;
          current.outEdges.add(b);
          current.bot = b.to;
          current.left = c.to;

        }
        if (j == 0 && i == this.height - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge b = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          current.top = a.to;
          current.outEdges.add(b);
          current.right = b.to;

        }
        if (j > 0 && j < this.width - 1 && i == this.height - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge b = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          Edge c = new Edge(current, this.maze.allVertices.get(i).get(j+1));
          current.top = a.to;
          current.left = b.to;
          current.outEdges.add(c);
          current.right = c.to;

        }
        if (j == this.width - 1 && i == this.height - 1) {
          Edge a = new Edge(current, this.maze.allVertices.get(i- 1).get(j));
          Edge b = new Edge(current, this.maze.allVertices.get(i).get(j-1));
          current.top = a.to;
          current.left = b.to;
          current.color = Color.BLUE;

        }
      }
    }
  }
  
  
 
  
 //EFFECT:If b is pressed, it uses breadth first and if d is pressed, it uses depth first search
 //to find the minimum spanning tree
 //Solves the maze
 public void onKeyEvent(String key) {
   if(key.equals("b")) {
     
   }
   else if(key.equals("d")) {
     
   }
   
   else {
     
   }
   
  }
 
 public void path() {
   
 }
}
class ExamplesMaze {
  
  Utils ut;
  Graph mt;
  void initData() {
    ut = new Utils();
    mt = new Graph();
  }
  
  //test for makeMaze method
  void testMakeMaze(Tester t) {
    initData();
    Graph ex1 = ut.makeMaze(1,1);
    MazeRunner ex2 = new MazeRunner(5, 5);
    t.checkExpect(mt.allVertices.size(), 0);
    mt.allVertices.add(new ArrayList<Vertex>(Arrays.asList(new Vertex(1,1,0))));
    t.checkExpect(ex1, mt);
    t.checkExpect(mt.allVertices.size(), 1);
    System.out.println(ex2.maze.allVertices.get(1).get(1).outEdges);
    System.out.println(ex2.maze.allVertices.get(1).get(1).bot);
  }
  
  //Big bang
  void testBigBang(Tester t) {
    MazeRunner game = new MazeRunner(10, 10);
    game.bigBang(game.width * 30, game.height * 30);
  }
  
}