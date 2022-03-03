module com.jdriven.jdkworkshop.recursivedivision {
    requires com.jdriven.jdkworkshop.division;

    provides com.jdriven.jdkworkshop.division.DivisionService with
            com.jdriven.jdkworkshop.recursivedivision.RecursiveSubstractionDivisionService;
}
