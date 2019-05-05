import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "E:\\New folder (2)\\Products.csv";


        System.out.println("starting write Product.csv file: " + filePath);
        writeCsv(filePath);

        System.out.println("starting read Product.csv file");
        //readCsv(filePath);
        final CSVParser parser = new CSVParserBuilder().withSeparator(',')
                        .withIgnoreQuotations(true)
                        .build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).withCSVParser(parser).build();

        ColumnPositionMappingStrategy<CsvShop> beanStrategy = new ColumnPositionMappingStrategy<CsvShop>();
        beanStrategy.setType(CsvShop.class);
        beanStrategy.setColumnMapping(new String[] {"productName","price","quantity"});

        CsvToBean<CsvShop> csvToBean = new CsvToBean<CsvShop>();


        //List<CsvShop> csvShops = csvToBean.parse(beanStrategy,reader);

        //FileReader reader2=new FileReader(filePath);
        List<CsvShop> beans =  new CsvToBeanBuilder(new FileReader(filePath)).withType(CsvShop.class).build().parse();

        //List<CsvShop> csvShops1= new CsvToBeanBuilder(reader2).;
        //System.out.println(emps);


    }

    public static void writeCsv(String filePath) {
        List<Product> products = new ArrayList<Product>();

        //create demo Products
        Product p1 = new Product();
        p1.setProductName("Samsung");
        p1.setPrice(1000);
        p1.setQuantity(231);
        products.add(p1);

        Product p2= new Product();
        p2.setProductName("Iphone");
        p2.setPrice(1000);
        p2.setQuantity(231);
        products.add(p2);



        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);

            fileWriter.append("ProductName, Price, Quantity\n");
            for(Product p: products) {
                fileWriter.append(String.valueOf(p.getProductName()));
                fileWriter.append(",");
                fileWriter.append(p.getPrice()+"");
                fileWriter.append(",");
                fileWriter.append((p.getQuantity()+""));
                fileWriter.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Product createProduct(String[] metadata){
        String productName = metadata[0];
        int price = Integer.parseInt(metadata[1]);
        int quantity = Integer.parseInt(metadata[2]);
        return new Product(productName, price, quantity);
    }

    public static void readCsv(String filePath) {
        BufferedReader reader = null;

        try {
            List<Product> products = new ArrayList<Product>();
            String line = "";
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();


            while((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                Product product=createProduct(fields);
                products.add(product);
            }

            for(Product p: products) {
                System.out.println(p.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}