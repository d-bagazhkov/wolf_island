package kodun.wolf.island;

public interface Configuration {

    Integer widthCeil = 80;
    Integer widthCeilBorder = 1;
    Integer countCeilWidth = 10;
    Integer countCeilHeight = 10;
    Integer width = countCeilWidth * widthCeil - (widthCeilBorder - 1) * countCeilWidth;
    Integer height = countCeilHeight * widthCeil - (widthCeilBorder - 1) * countCeilHeight;

}
