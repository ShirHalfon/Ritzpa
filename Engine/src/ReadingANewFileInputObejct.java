public class ReadingANewFileInputObejct implements IInputObject{

    public String fileNameToReadFrom;
    public ConcreteEngine allStocksInOurDataStructures;//need to remove after Engin becomes Static

    public ReadingANewFileInputObejct(String FileNameToReadFrom, ConcreteEngine allStocksInOurDataStructures) {
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

    @Override
    public String toString() {
        return "Reading a new file from: " + fileNameToReadFrom;
    }
}
