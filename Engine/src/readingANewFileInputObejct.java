public class readingANewFileInputObejct implements IInputObject{

    public String fileNameToReadFrom;
    public ConcreteEngine allStocksInOurDataStructures;//need to remove after Engin becomes Static

    public readingANewFileInputObejct(String FileNameToReadFrom, ConcreteEngine allStocksInOurDataStructures) {
        this.fileNameToReadFrom = FileNameToReadFrom;
        this.allStocksInOurDataStructures = allStocksInOurDataStructures;
    }

    public String getFileNameToReadFrom() {
        return fileNameToReadFrom;
    }

    public void setFileNameToReadFrom(String fileNameToReadFrom) {
        this.fileNameToReadFrom = fileNameToReadFrom;
    }

    public ConcreteEngine getAllStocksInOurDataStructures() {
        return allStocksInOurDataStructures;
    }

    public void setAllStocksInOurDataStructures(ConcreteEngine allStocksInOurDataStructures) {
        this.allStocksInOurDataStructures = allStocksInOurDataStructures;
    }
}
