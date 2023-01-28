package WS01;

import java.util.Arrays;

/**
 * おみくじのenumです
 */
public enum OMIKUJI {
  DAIKITI( "大吉", "<img src='https://bit.ly/3l1w58F' />"),
  CHUKITI("中吉", "<img src='https://bit.ly/349WrOY' />"),
  KITI("吉", "<img src='https://bit.ly/3jh7v31' />"),
  ;

  private String unsei;
  private String img;

  OMIKUJI(String unsei, String img) {
    this.unsei = unsei;
    this.img = img;
  }

  public String getUnsei() {
    return unsei;
  }

  public String getImg() {
    return img;
  }

  public static OMIKUJI getOMIKUJI(int num) {
    return Arrays.stream(OMIKUJI.values())
        .filter(value -> value.ordinal() == num)
        .findFirst()
        .orElse(null);
  }

}
