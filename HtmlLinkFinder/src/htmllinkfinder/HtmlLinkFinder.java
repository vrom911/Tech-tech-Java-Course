package htmllinkfinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Finding all links of the web-page given by user
 * @version 2.0     13/03/2016
 * @author Veronika Romashkina
 * @email    vrom911@gmail.com
 */
public class HtmlLinkFinder {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Welcome!");
        Scanner link = new Scanner(System.in);
        System.out.println("Please, enter the link");
        String input = link.next();
        String[] links = new String[150];
        int cur_ind = 0;

        try {
            URL myURL = new URL(input);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(myURL.openStream()));
            StringBuilder htmlBuilder = new StringBuilder();
            int symb = in.read();
            while (symb != -1) {
                htmlBuilder.append((char) symb);
                symb = in.read();
            }
            //  when done go back to String
            String htmlText = htmlBuilder.toString();
            Pattern aTag = Pattern.compile("<a([^>]+)>(.+?)</a\\s*>");
            Pattern hrefLink = Pattern.compile("href\\s*=\\s*(['\"]([^\"']*['\"]))");
            Matcher m = aTag.matcher(htmlText);
            while (m.find()) {
                Matcher href = hrefLink.matcher(m.group());
                if (href.find()){
                    String hrefStr = href.group(2).substring(0,href.group(2).length() - 1);
                    links[cur_ind] = hrefStr;
                    cur_ind++;
                } 
            } 
        
            Pattern httpEx = Pattern.compile("^(https?)://");
            Pattern ftpEx = Pattern.compile("^(ftp)://");
            Pattern mailtoEx = Pattern.compile("^mailto:");
            int httpLink =0, ftpLink = 0, mailtoLink = 0, otherLink = 0;
            for (int j = 0; links[j] != null; j++) {
                System.out.println(links[j]);
                Matcher href1 = httpEx.matcher(links[j]);
                Matcher href2 = ftpEx.matcher(links[j]);
                Matcher href3 = mailtoEx.matcher(links[j]);
                if(href1.find()) {
                    httpLink++;
                }
                else if(href2.find()) {
                    ftpLink++;
                }
                else if(href3.find()) {
                    mailtoLink++;
                }
                else {
                    otherLink++;
                }
            }
            System.out.println("HTTP:  " + httpLink + "\nFTP:  " + ftpLink + "\nMAILTO:  " + mailtoLink + "\nOthers:  " + otherLink);

        } 
        catch (MalformedURLException e) {
            System.err.println("HEY YOU!");
        }
//        Pattern httpEx = Pattern.compile("^(https?)://");
//        Pattern ftpEx = Pattern.compile("^(ftp)://");
//        Pattern mailtoEx = Pattern.compile("mailto:");
//        int ftpLink = 0, mailtoLink = 0, otherLink = 0;
//        for (int j = 0; links[j] != null; j++) {
//            System.out.println(links[j]);
//            
//            
//        }

    }
    
}
