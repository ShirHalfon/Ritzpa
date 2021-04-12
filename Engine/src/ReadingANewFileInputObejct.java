public class ReadingANewFileInputObejct implements IInputObject{

    public String i_FileNameToReadFrom;
    public ConcreteEngine allStocksInOurDataStructures;//need to remove after Engin becomes Static

    public ReadingANewFileInputObejct(String i_FileNameToReadFrom, ConcreteEngine allStocksInOurDataStructures) {
        this.i_FileNameToReadFrom = i_FileNameToReadFrom;
        this.allStocksInOurDataStructures = allStocksInOurDataStructures;
    }

    public String getI_FileNameToReadFrom() {
        return i_FileNameToReadFrom;
    }

    public void setI_FileNameToReadFrom(String i_FileNameToReadFrom) {
        this.i_FileNameToReadFrom = i_FileNameToReadFrom;
    }

    public ConcreteEngine getAllStocksInOurDataStructures() {
        return allStocksInOurDataStructures;
    }

    public void setAllStocksInOurDataStructures(ConcreteEngine allStocksInOurDataStructures) {
        this.allStocksInOurDataStructures = allStocksInOurDataStructures;
    }
}
