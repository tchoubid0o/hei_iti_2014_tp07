package tp07.front.data;

public final class Singleton {

  private static Singleton instance = null;

  private Slide slide;

  private Singleton() {
      super();
      this.slide = new Slide();
  }

  public final static Singleton getInstance() {
      if (Singleton.instance == null) {
         synchronized(Singleton.class) {
           if (Singleton.instance == null) {
             Singleton.instance = new Singleton();
           }
         }
      }
      return Singleton.instance;
  }

public Slide getSlide() {
	return slide;
}

public void setSlide(Slide slide) {
	this.slide = slide;
}
  

}