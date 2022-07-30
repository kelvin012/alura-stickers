import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    public void criar(InputStream inputStream, String nomeArquivo) throws IOException {

        // leitura da imagem
        // InputStream inputStream = new FileInputStream("entrada/filme-maior.jpg");
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memoria com transparencia e  com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font("impact", Font.BOLD, 64);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        String mensagem = "TOPZERA";
        int centralizarMensagem = (int) (largura / 3.3);
        graphics.drawString(mensagem, centralizarMensagem, novaAltura - 100);

        // fazer o contorno(outline) na mensagem
        TextLayout tl = new TextLayout(mensagem, fonte, graphics.getFontRenderContext());
        Shape shape = tl.getOutline(null);

        graphics.translate(centralizarMensagem, novaAltura - 100);

        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(2f));
        graphics.draw(shape);

        // escrever  a imagem final  em disco
        File imagemFinal = new File(nomeArquivo);
        if (!imagemFinal.getParentFile().exists()) imagemFinal.getParentFile().mkdirs();
        ImageIO.write(novaImagem, "png", imagemFinal);

    }

//    public static void main(String[] args) throws IOException {
//        StickerGenerator stickerGenerator = new StickerGenerator();
//        InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
//        stickerGenerator.criar(inputStream, "saida/filme5.png");
//    }
}
