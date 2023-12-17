private class temp {
    private static final String hello = "hello";

    private int age;
    private boolean someBool;

    private temp() {
        System.out.println("private");
    }

    private void testMethod(int i) {
        System.out.println(i);
    }
}
