import java.util.*;
import java.io.*;

public class Dictionary
{
  public String fileName;
  ArrayList<String> words = new ArrayList<String>();

  public Dictionary(String fileName)
  {
    this.fileName = fileName;
    loadFile();
  }

  public void loadFile()
  {
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line = "";

      while((line = br.readLine())!= null)
      {
        words.add(line);
      }
    }
    catch(FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  public String findClosest(String toFind)
  {
    int closestEd = Integer.MAX_VALUE;
    String closestWord = "";

    for(String word:words)
    {
      int ed = EditDistance.EditDist(toFind,word);

      if(ed == 0)
      {
        return toFind;
      }
      else if(ed < closestEd)
      {
        closestEd = ed;
        closestWord = word;
      }
    }

    return closestWord;
  }

  public String toString()
  {
    StringBuffer sb =  new StringBuffer();

    for(String word:words)
    {
      sb.append(word+"\n");
    }

    return ""+sb;
  }
}
