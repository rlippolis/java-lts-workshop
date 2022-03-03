module com.jdriven.jdkworkshop.simpledivision {
    requires com.jdriven.jdkworkshop.division;

    provides com.jdriven.jdkworkshop.division.DivisionService with
            com.jdriven.jdkworkshop.simpledivision.SimpleDivisionService;
}