package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class Tools {
	// de 1 genero 000001A (seguido de la letra del NIF)
	public static String creaSerie(int a) {
		return String.format("%06d%c", a,calculaLetra(a));
		
	}
	private static char calculaLetra(int dni) {
		String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE";
		return juegoCaracteres.charAt(dni % 23);
	}
	
	// Esta versión genera un pdf con el código de barras cod39 del valor de parámetro code
	// EL fichero generado se guarda con el nombre Tabla seguido de _ y valor de code (terminado .pdf)
	//   Ejem: Para Tabla=Socio y code=000001A , el nombre sería Socio_000001A.pdf
	// La ruta siempre debe terminar en /
	
	public static String creaFicheroPDF(String ruta,String Tabla,String code) throws DocumentException, FileNotFoundException {
		String file = ruta+Tabla+"_"+code+".pdf";
        FileOutputStream archivo = new FileOutputStream(file);
        Rectangle pagesize=new Rectangle(100,50);
        Document documento = new Document(pagesize);
        documento.setMargins(5, 5, 5, 5);
        PdfWriter pdfw = PdfWriter.getInstance(documento, archivo);
        documento.open();

        PdfContentByte cb = pdfw.getDirectContent();

        Barcode39 code39 = new Barcode39();
        code39.setCode(code);
        //documento.add(new Paragraph("Imprime y recorta el siguiente código de barras", new Font(Font.FontFamily.HELVETICA, 12)));
        //documento.add(new Paragraph("Pégalo en el libro con un poco de celofán ancho", new Font(Font.FontFamily.HELVETICA, 12)));
        //documento.add(new Paragraph("  ", new Font(Font.FontFamily.HELVETICA, 12)));
        //documento.add(new Paragraph("  ", new Font(Font.FontFamily.HELVETICA, 12)));
        documento.add(code39.createImageWithBarcode(cb, null, null));
        documento.close();
        return file;
	}
	
	// En esta versión evitamos el uso de fichero y generamos el pdf al vuelo
	

}
