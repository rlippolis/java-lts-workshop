module com.jdriven.jdkworkshop.client {
    requires com.jdriven.jdkworkshop.division;
    uses com.jdriven.jdkworkshop.division.DivisionService;

    exports com.jdriven.jdkworkshop.client;
}