public class ReverseString {
    public static void main(String[] args) {

        String mainString = "Naber müdür";
        String reverseString = "";

        for (int i = 0 ; i < mainString.length(); i++){
            reverseString = mainString.charAt(i) + reverseString;
        }
        System.out.println("Reversed string is: " + reverseString);
    }
}