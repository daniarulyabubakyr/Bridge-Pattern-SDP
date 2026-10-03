public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Run with --demo");
        }
    }

    private static void runDemo() {
        int passed = 0;

        passed += testT1() ? 1 : 0;
        passed += testT2() ? 1 : 0;
        passed += testT3() ? 1 : 0;
        passed += testT4() ? 1 : 0;
        passed += testT5() ? 1 : 0;
        passed += testT6() ? 1 : 0;
        passed += testT7() ? 1 : 0;

        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }

    private static boolean testT1() {
        Circle circle = new Circle(
                "C1",
                new VectorRenderer(),
                2
        );

        String actual = circle.execute();
        String expected = "VECTOR circle radius=2";

        boolean pass = actual.equals(expected);

        printResult(
                "T1",
                pass,
                "Circle + VectorRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT2() {
        Circle circle = new Circle(
                "C1",
                new RasterRenderer(),
                2
        );

        String actual = circle.execute();
        String expected = "RASTER circle radius=2";

        boolean pass = actual.equals(expected);

        printResult(
                "T2",
                pass,
                "Circle + RasterRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT3() {
        Square square = new Square(
                "S1",
                new VectorRenderer(),
                3
        );

        String actual = square.execute();
        String expected = "VECTOR square side=3";

        boolean pass = actual.equals(expected);

        printResult(
                "T3",
                pass,
                "Square + VectorRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT4() {
        Square square = new Square(
                "S1",
                new RasterRenderer(),
                3
        );

        String actual = square.execute();
        String expected = "RASTER square side=3";

        boolean pass = actual.equals(expected);

        printResult(
                "T4",
                pass,
                "Square + RasterRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT5() {
        Circle circle = new Circle(
                "C1",
                new VectorRenderer(),
                2
        );

        Circle originalReference = circle;

        String before = circle.execute();

        String originalId = circle.getId();
        int originalRadius = circle.getRadius();

        circle.setImplementation(new RasterRenderer());

        String after = circle.execute();

        boolean sameObject = circle == originalReference;

        boolean stateUnchanged =
                circle.getId().equals(originalId)
                        && circle.getRadius() == originalRadius;

        String expectedBefore = "VECTOR circle radius=2";
        String expectedAfter = "RASTER circle radius=2";

        boolean pass =
                sameObject
                        && stateUnchanged
                        && before.equals(expectedBefore)
                        && after.equals(expectedAfter);

        if (pass) {
            System.out.println(
                    "T5 PASS | sameObject=" + sameObject +
                            " | stateUnchanged=" + stateUnchanged
            );

            System.out.println(
                    "  before=" + before +
                            " | after=" + after
            );
        } else {
            System.out.println(
                    "T5 FAIL | sameObject=" + sameObject +
                            " | stateUnchanged=" + stateUnchanged
            );

            System.out.println(
                    "  before=" + before +
                            " | after=" + after
            );

            System.out.println(
                    "  expectedBefore=" + expectedBefore +
                            " | expectedAfter=" + expectedAfter
            );
        }

        return pass;
    }

    private static boolean testT6() {
        Circle circle = new Circle(
                "C1",
                new AsciiRenderer(),
                2
        );

        String actual = circle.execute();
        String expected = "ASCII circle radius=2";

        boolean pass = actual.equals(expected);

        printResult(
                "T6",
                pass,
                "Circle + AsciiRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static boolean testT7() {
        Square square = new Square(
                "S1",
                new AsciiRenderer(),
                3
        );

        String actual = square.execute();
        String expected = "ASCII square side=3";

        boolean pass = actual.equals(expected);

        printResult(
                "T7",
                pass,
                "Square + AsciiRenderer",
                actual,
                expected
        );

        return pass;
    }

    private static void printResult(
            String testId,
            boolean pass,
            String classes,
            String actual,
            String expected
    ) {
        if (pass) {
            System.out.println(
                    testId + " PASS | " +
                            classes + " | result=" + actual
            );
        } else {
            System.out.println(
                    testId + " FAIL | " +
                            classes +
                            " | actual=" + actual +
                            " | expected=" + expected
            );
        }
    }
}