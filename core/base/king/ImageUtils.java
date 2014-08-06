package base.king;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;  
import java.awt.image.BufferedImage;  
import java.awt.image.CropImageFilter;  
import java.awt.image.FilteredImageSource;  
import java.awt.image.ImageFilter;  
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.IOException;  
import javax.imageio.ImageIO;    
public class ImageUtils {  
  
	public BufferedImage loadImage(String filepath) throws IOException{
		BufferedImage file=javax.imageio.ImageIO.read(new File(filepath));
		return file;
	}
	public void writeImage(BufferedImage _file,String filepath) throws IOException{
		ImageIO.write(_file, "JPEG", new File(filepath));
	}
    /** 
     * 对图片进行缩放 
     *  
     * @param srcImgFileName 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    public BufferedImage zoomImage(BufferedImage file,int width,int height) throws IOException {         

        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        temp.getGraphics().drawImage(file, 0, 0, width, height, null);  
        return temp;
    }  
  
    /** 
     * 将图片进行切割
     *  
     * @param srcImageFile 
     * @throws IOException 
     */  
    public BufferedImage cutImage(BufferedImage file,int x,int y,int width,int height) throws IOException {  
        ImageFilter cropFilter = new CropImageFilter(x, y,width, height);
        Image img = Toolkit.getDefaultToolkit().createImage(
				new FilteredImageSource(file.getSource(), cropFilter));
		BufferedImage temp = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = temp.getGraphics();
		g.drawImage(img, 0, 0, null); // 绘制小图
		g.dispose();
		return temp;
    }  
  
    public static void create17060(){
        ImageUtils iu = new ImageUtils();
    	try {
			BufferedImage file=iu.loadImage("d:\\temp\\1.jpg");
			BufferedImage _file=null;
			int width=file.getWidth();
			int height=file.getHeight();
			int w=170;
			int h=60;
			if(width/height>w/h){
				//宽超出
				_file=iu.zoomImage(file, width*h/height, h);
			}else{
				//高超出
				_file=iu.zoomImage(file, w, height*w/width);
			}
			_file=iu.cutImage(_file, 0, 0, w, h);
			iu.writeImage(_file, "d:\\temp\\2.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) throws IOException {  
    	create17060();
    }  
}  