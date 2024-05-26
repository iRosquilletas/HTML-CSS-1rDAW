package sambjavax;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDF_register {

    public void generateUserPDF(List<Register> data) throws IOException {
        // Crear un nuevo documento PDF
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Cargar la fuente personalizada
            PDType0Font font = PDType0Font.load(document, new File("/home/samy/Desktop/Fuentes/Helvetica.ttf"));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Configurar la fuente y el tamaño del texto
                contentStream.setFont(font, 12);

                // Escribir el encabezado
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Lista de Usuarios");
                contentStream.endText();

                // Escribir la tabla
                int row = 1;
                for (int i = 0; i < data.size(); i++) {
                    Register user = data.get(i);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700 - row * 20);
                    contentStream.showText((i + 1) + ". " + user.getUsuario() + "\t" + user.getRol());
                    contentStream.endText();
                    row++;
                }
            }

            // Guardar el documento PDF en un archivo
            document.save("lista_usuarios.pdf");
        }
    }
}
