package org.huohua.drawsomething;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Main extends HttpServlet {

    TrieTree tree;

    public void init(ServletConfig config)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String word =null;
            ArrayList<String> words = new ArrayList<String>();
            while((word = br.readLine())!=null)
            {
                words.add(word);
            }
            tree = new TrieTree(words);

            super.init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ShowFirst(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out=response.getWriter();
        out.println(
        "<html>"+
            "<head><title>DrawSomethingHelper</title></head>"+
                "<body>"+
                "<form action=\"\">"+
                    "<input name=\"chars\"></input>"+
                    "<input name=\"num\"></input>"+
                    "<input type=\"submit\"></input>"+
                "</form>"+
            "</body>"+
        "</html>");
        out.flush();
    }
    private void ShowQuery(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Map<String,String[]> mapParams = request.getParameterMap();
        PrintWriter out=response.getWriter();
        String strChars = mapParams.get("chars")[0];
        String strNum = mapParams.get("num")[0];

        out.println(
        "<html>" +
            "<head>" +
                "<title>DrawSomethingHelper</title>" +
            "</head>" +
            "<body>" +
                "<form action=\"\">" +
                "CharSet<input name=\"chars\" value=\""+strChars+"\">Length</input>" +
                "<input name=\"num\" value=\""+strNum+"\"></input>" +
                "<input type=\"submit\" ></input>" +
                "</form>" +
            "</body>" +
        "</html>");

        tree.FindDrawSomething(strChars,Integer.parseInt(strNum));
        for(Iterator<String> it = TrieNode.Result.iterator(); it.hasNext(); )
        {
            out.println(it.next());
        }

        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Map<String,String[]> mapParams = request.getParameterMap();
        if (mapParams.containsKey("chars") && mapParams.containsKey("num"))
            ShowQuery(request,response);
        else
            ShowFirst(request,response);

    }
}