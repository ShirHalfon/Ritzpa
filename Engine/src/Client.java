public class Client {

    public void ReadingANewFile(){
        /*
        * Need to read the XML
        * Validation on Data
        * Copy all the data
        * */
    }

    public void ShowAllStocks(){
        /**EACH SHOW ISNT REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
        * For each stock show:
        * 1. Symbol
        * 2. Company Name
        * 3. Price
        * 4. number of Deals made so far
        * 5. Sum of all deals made so far (can use the Stream Method)
        * Note: This can't be chosen without a file in database
        * */
    }

    public void ShowSingleStock(){
        /**EACH SHOW ISNT REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
        * 1. Symbol
         * 2. Company Name
         * 3. Price
         * 4. number of Deals made so far
         * 5. Sum of all deals made so far (can use the Stream Method)
         * 6. Show all deals
         *      a. Copy the list
         *      b. Sort by latest to earliest
         *      c. For each deal show:
         *          - Date (with the specified format)
         *          - Amount of stocks
         *          - Price
         *          - Total worth
        * */
    }

    public void OrderAction(){
        /**EACH SHOW ISNT REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
        * 1. Input:
        *       a. Type (sell/buy)
        *       b. Symbol (can be in any case, use ignorecase method)
        *       c. Amount
        *       d. Limit - Notice the restrictions in the spec
        * 2. Validations:
        *       a. Symbol - does it exist
        *       b. Amount > 0
        * 3. Add to Order a timestamp
        * 4. Activate action
        *       a. Selling will be encountered with Buying orders
        *       b. Buying will be encountered with Selling orders
        * Note: note all the specifications - This is the heart of the exercise - Think of how and where to actually
        * implement it
        * */
    }

    public void ShowOrdersForAllStocks(){

        /**EACH SHOW ISNT REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
        * 1. For each Stock(!!) show the following lists:
        *       a. Buying Orders
        *       b. Selling Orders
        *       c. Deals Made
        * 2. For each Order show the following:
        *       a. Date
        *       b. Amount
        *       c. Price
        *       d. Cycle
        * 3. Sum of the Cycle for each list
        * */
    }

    public void Exit(){

        /*
        * Exit the system
        * If we can make it - add the option to save the system's status - using serialization
        * If we save then we need to add loading the status if it exists
        * */
    }
}
