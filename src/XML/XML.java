package XML;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class XML{
    XStream xstream = new XStream(new StaxDriver());
    
    public void simpanXML(ArrayList arrayUser){
        XStream xstream = new XStream(new StaxDriver());
        //modul untuk menyimpan
        String xml = xstream.toXML(arrayUser);
        
        FileOutputStream coba = null;
        try{
            //membuat nama file dan folder tempat menyimpan jika perlu
            coba = new FileOutputStream("User.xml");
            
            //mengubah karakter penyusun string xml
            //sebagai bytes(berbentuk nomor" kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");
            
            //menyimpan file dari bytes
            coba.write(bytes);
        }
        catch(Exception e){
            System.err.println("Perhatian: " + e.getMessage());
        }
        finally{
            if(coba != null){
                try{
                    coba.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public ArrayList bukaXML(){
        FileInputStream cobi = null;
        try{
            //nama file yang akan dibuka (termasuk folder jika perlu)
            cobi = new FileInputStream("User.xml");
            //harus diingat objek apa yang dahulu disimpan di file
            //program untuk membaca harus sinkron dengan program
            //yang dahuulu digunakan untuk menyimpannya
            int isi;
            char charnya;
            String Stringnya;
            //isi file dikembalikan menjadi string
            Stringnya="";
            while((isi = cobi.read()) != -1){
                charnya=(char) isi;
                Stringnya = Stringnya + charnya;
            }
            //string isi file dikembalikan menjadi larik
            return (ArrayList) xstream.fromXML(Stringnya);
        }
        catch(Exception e){
            System.err.println("test: " + e.getMessage());        
            return new ArrayList<>();
        }
        finally{
            if(cobi != null){
                try{
                    cobi.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    
}
