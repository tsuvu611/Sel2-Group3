package enums;

public enum DataProfile {
    ActionImplementationByStatus("Action Implementation By Status"),
    FunctionalTests("Functional Tests"),
    TestCaseExecution("Test Case Execution"),
    TestCaseExecutionFailureTrend("Test Case Execution Failure Trend"),
    TestCaseExecutionResults("Test Case Execution Results"),
    TestCaseExecutionTrend("Test Case Execution Trend"),
    TestModuleExecution("Test Module Execution"),
    TestModuleExecutionFailureTrend("Test Module Execution Failure Trend"),
    TestModuleExecutionFailureTrendbyBuild("Test Module Execution Failure Trend by Build"),
    TestModuleExecutionHistory("Test Module Execution History"),
    TestModuleExecutionResults("Test Module Execution Results"),
    TestModuleExecutionResultsReport("Test Module Execution Results Report"),
    ModuleExecutionTrend("Test Module Execution Trend"),
    TestModuleExecutionTrendbyBuild("Test Module Execution Trend by Build"),
    TestModuleImplementationByPriority("Test Module Implementation By Priority"),
    TestModuleImplementationByStatus("Test Module Implementation By Status"),
    TestModuleStatusperAssignedUsers("Test Module Status per Assigned Users"),
    TestObjectiveExecution("Test Objective Execution"),
    TestCaseExecutionHistory("Test Case Execution History");

    private final String value;

    DataProfile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
