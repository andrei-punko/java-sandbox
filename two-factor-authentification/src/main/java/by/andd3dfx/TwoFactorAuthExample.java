package by.andd3dfx;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

/**
 * See for reference: https://github.com/j256/two-factor-auth
 */
public class TwoFactorAuthExample {

    public static void main(String[] args) throws Exception {

        String base32Secret = TimeBasedOneTimePasswordUtil.generateBase32Secret();
        System.out.println("secret = " + base32Secret);

        // this is the name of the key which can be displayed by the authenticator program
        String keyId = "andd3dfx@gmail.com";
        // generate the QR code
        String qrImageUrl = TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, base32Secret)
            .replace("%3F", "?").replace("%3D", "=");   // My fixes for the '?' and '=' characters representation
        System.out.println("Image url = " + qrImageUrl);
        // we can display this image to the user to let them load it into their auth program

        // we can use the code here and compare it against user input
        String code = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret);

        /*
         * this loop shows how the number changes over time
         */
        while (true) {
            long diff = TimeBasedOneTimePasswordUtil.DEFAULT_TIME_STEP_SECONDS
                - ((System.currentTimeMillis() / 1000) % TimeBasedOneTimePasswordUtil.DEFAULT_TIME_STEP_SECONDS);
            code = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret);
            System.out.println("Secret code = " + code + ", change in " + diff + " seconds");
            Thread.sleep(1000);
        }
    }
}
