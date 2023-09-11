/*
 * Copyright 2023 Park Jun-Hong_(parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "cipher-utils".
 *
 * Date  : 2023. 8. 23. 오후 3:27:21
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
package open.commons.tools.cipher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import open.commons.core.utils.EncryptUtils;
import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2023. 8. 23.
 * @version _._._
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class CipherUtils {

    private static final String OPT_FLAG_METHOD = "-m";
    private static final String METHOD_ENCRYPT = "enc";
    private static final String METHOD_DECRYPT = "dec";

    private static final String OPT_FLAG_KEY = "-k";
    private static final String OPT_FLAG_DATA = "-d";

    private static final int LAST_FLAG_METHOD = 0;
    private static final int LAST_FLAG_KEY = 1;
    private static final int LAST_FLAG_DATA = 2;
    private static final int LAST_DATA = 3;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 8. 23.		박준홍			최초 작성
     * </pre>
     *
     * @since 2023. 8. 23.
     */
    public CipherUtils() {
    }

    private static void decryp(String key, String data) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(data);
            String plainText = EncryptUtils.decrypt(key, decodedData);
            System.out.print(plainText);

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private static void encryp(String key, String data) {
        try {
            byte[] encryptedData = EncryptUtils.encrypt(key, data);
            String encodedData = new String(Base64.getEncoder().encode(encryptedData));
            System.out.print(encodedData);

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private static void help() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(CipherUtils.class.getResourceAsStream("/open/commons/tools/cipher/help.txt")))) {
            String readline = null;
            while ((readline = reader.readLine()) != null) {
                System.out.println(readline);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        if (args == null || args.length < 6) {
            System.out.println("올바르지 않은 정보입니다. 입력=" + (args == null ? "" : Arrays.toString(args)));
            help();
            System.exit(0);
        }

        String method = null;
        String key = null;
        String data = null;
        int lastArg = -1;

        // 옵션 확인
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case OPT_FLAG_METHOD:
                    lastArg = LAST_FLAG_METHOD;
                    break;
                case OPT_FLAG_KEY:
                    lastArg = LAST_FLAG_KEY;
                    break;
                case OPT_FLAG_DATA:
                    lastArg = LAST_FLAG_DATA;
                    break;
                default:
                    switch (lastArg) {
                        case LAST_FLAG_METHOD:
                            switch (args[i]) {
                                case METHOD_ENCRYPT:
                                case METHOD_DECRYPT:
                                    method = args[i];
                                    break;
                                default:
                                    help();
                                    System.exit(0);
                                    break;
                            }
                            break;
                        case LAST_FLAG_KEY:
                            key = args[i];
                            break;
                        case LAST_FLAG_DATA:
                            data = args[i];
                            break;
                    }
                    lastArg = LAST_DATA;
                    break;
            }
        }

        // 데이터 검증
        if (StringUtils.isNullOrEmptyStringAnd(method, key, data)) {
            help();
            System.exit(0);
        }

        switch (method) {
            case METHOD_ENCRYPT:
                encryp(key, data);
                break;
            case METHOD_DECRYPT:
                decryp(key, data);
                break;
            default:
                // unreachable code
                break;
        }

    }
}
