import org.eclipse.swt.graphics.Image;

public class Casella implements Comparable{

		private String nome;
		private Image image;
		
		public Casella(){
			this.nome = "";
			this.image = null;
		}
		public Casella(String nome, Image image){
			this.nome = nome;
			this.image = image;
		}
		public Casella(Casella c){
			this.nome = c.nome;
			this.image = c.image;
		}
		public boolean equals(Casella c){
			return (this.nome.equals(c.nome) && this.image.equals(c.image));	
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Image getImage() {
			return image;
		}
		public void setImage(Image image) {
			this.image = image;
		}
		public String toString() {
			return "Casella: " + nome + ", " + image;
		}
		public Casella clone(){
			return new Casella(this);
		}
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			if(arg0.equals(this) && (arg0 instanceof Casella)){
				return 0;
			}else{
				return 1;
			}
			//return 1;
		}
		public boolean compareTo(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			if(arg0.equals(this) && (arg0 instanceof Casella) && (arg1.equals(this) && (arg1 instanceof Casella))){
				return true;
			}else{
				return false;
			}
			//return 1;
		}
}
