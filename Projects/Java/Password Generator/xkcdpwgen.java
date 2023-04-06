import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class xkcdpwgen {
  public static void main(String[] args) throws IOException {
    int[] numlist = {0,1,2,3,4,5,6,7,8,9};
    String[] symbols = {"~","!","@","#","$","%","^","&","*",".",":",";"};
    List<String> content = Files.readAllLines(Paths.get("./words.txt"));
    int length = content.size();
    int count = 4;
    ArrayList<String> words = new ArrayList<>();

    while(count > 0) {
      int rand = new Random().nextInt(length);
      words.add(content.get(rand));
      count --;
    }

    for(int i = 0; i < args.length; i++) {
      if(args[i].equals("-h")) {
        System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\n"+
                "Generate a secure, memorable password using the XKCD method\n"+
                "optional arguments:\n"+
                "-h, --help            show this help message and exit\n"+
                "-w WORDS, --words WORDS\n"+
                "include WORDS words in the password (default=4)\n"+
                "-c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\n"+
                "(default=0)\n"+
                "-n NUMBERS, --numbers NUMBERS\n"+
                "insert NUMBERS random numbers in the password\n"+
                "(default=0)\n"+
                "-s SYMBOLS, --symbols SYMBOLS\n"+
                "insert SYMBOLS random symbols in the password\n"+
                "(default=0)");
      }
      if(args[i].equals("-w") && i + 1 < args.length) {
        words = new ArrayList<>();
        try {
          count = Integer.parseInt(args[i + 1]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid input!");
          break;
        }
        while(count > 0) {
          int rand = new Random().nextInt(length);
          words.add(content.get(rand));
          count --;
        }
      }
      if(args[i].equals("-c") && i + 1 < args.length) {
        int start = 0;
        int num;
        try {
          num = Integer.parseInt(args[i + 1]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid input!");
          break;
        }

        while (start < num) {
          String current = words.get(start);
          words.set(start, current.substring(0,1).toUpperCase() + current.substring(1));
          start++;
        }
      }
      if(args[i].equals("-n") && i + 1 < args.length) {
        int num;
        int start = 0;
        try {
          num = Integer.parseInt(args[i + 1]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid input!");
          break;
        }
        while (start < num) {
          int randword = new Random().nextInt(words.size());
          int randnum = new Random().nextInt(numlist.length);
          String gen = Integer.toString(numlist[randnum]);
          String current = words.get(randword);
          int randspace = new Random().nextInt(current.length());
          words.set(randword,addChar(current,gen,randspace));
          start++;
        }
      }
      if(args[i].equals("-s") && i + 1 < args.length) {
        int num;
        int start = 0;
        try {
          num = Integer.parseInt(args[i + 1]);
        } catch (NumberFormatException e) {
          System.out.println("Invalid input!");
          break;
        }
        while (start < num) {
          int randword = new Random().nextInt(words.size());
          int randsymb = new Random().nextInt(symbols.length);
          String gen = symbols[randsymb];
          String current = words.get(randword);
          int randspace = new Random().nextInt(current.length());
          words.set(randword,addChar(current,gen,randspace));
          start++;
        }
      }
    }
    String result = "";
    for(String word: words) {
      result +=word;
    }
    System.out.println(result);
  }
  public static String addChar(String str, String c, int pos) {
    return str.substring(0, pos) + c + str.substring(pos);
  }
}
