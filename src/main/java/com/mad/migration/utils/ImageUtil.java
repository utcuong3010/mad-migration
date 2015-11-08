package com.mad.migration.utils;

///**
// * @author vietlp
// *
// */
public class ImageUtil {
//
//    public static final String JPEG = "jpeg";
//    @SuppressWarnings("serial")
//    public static final Map<String, Float> ASPECT_RATIO_MAP = new HashMap<String, Float>() {
//        {
//            put("4x3", 4F / 3F);
//            put("3x4", 3F / 4F);
//            put("2x3", 2F / 3F);
//            put("16x9", 16F / 9F);
//        }
//    };
//
//    /**
//     * Encode image to string
//     *
//     * @param image
//     *            The image to encode
//     * @param type
//     *            jpeg, bmp, ...
//     * @return encoded string
//     */
//    public static String encodeByteArrayToString(BufferedImage image, String type) {
//        String imageString = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        try {
//            ImageIO.write(image, type, bos);
//            byte[] imageBytes = bos.toByteArray();
//
//            imageString = Base64.encodeBase64String(imageBytes);
//
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return imageString;
//    }
//
//    public static String encodeImageToString(byte[] image) {
//        return Base64.encodeBase64String(image);
//    }
//
//    /**
//     * Calculate aspect ratio of an image Assume that c is common divisor of width and height, calculated by
//     * commonDivisor(w, h). Then aspect ratio is calculate by w/c:h/c.
//     *
//     * @param width
//     *            width of an image, in pixels
//     * @param height
//     *            height of an image, in pixels
//     * @return a string, in form wxh, like 2x3, 3x4, 16x9
//     */
//    public static String buildAspectRatioString(int width, int height) {
//        return width / ImageUtil.commonDivisor(width, height) + "x" + height / ImageUtil.commonDivisor(width, height);
//    }
//
//    /**
//     * Calculate aspect ratio of an image Assume that c is common divisor of width and height, calculated by
//     * commonDivisor(w, h). Then aspect ratio is calculated by w/c:h/c and accepted 10% delta compare to aspect ratio
//     * list.
//     *
//     * @param width
//     *            width of an image, in pixels
//     * @param height
//     *            height of an image, in pixels
//     * @return a string, in form wxh, like 2x3, 4x3, 16x9
//     */
//    public static String buildAspectRatio(int width, int height) {
//        float ratio = (float) width / height;
//        for (Map.Entry<String, Float> entry : ASPECT_RATIO_MAP.entrySet()) {
//            // Accepted 10% delta
//            if (Math.abs(ratio - entry.getValue()) <= 0.1) {
//                return entry.getKey();
//            }
//        }
//
//        return ImageUtil.buildAspectRatioString(width, height);
//    }
//
//    /**
//     * Get the common divisor of 2 integer.
//     *
//     * @param a
//     *            the a
//     * @param b
//     *            the b
//     * @return common divisor of a and b
//     */
//    public static int commonDivisor(int a, int b) {
//        if (a == 0 || b == 0) {
//            return a + b;
//        }
//        while (a != b) {
//            if (a > b) {
//                a = a - b;
//            } else {
//                b = b - a;
//            }
//        }
//        return a;
//    }
//
//    public static Orientation getImageOrientation(int width, int height) {
//        double imageAspectRatio = ((double) width) / ((double) height);
//        if (imageAspectRatio > 1) // landscape poster
//        {
//            return Orientation.LANDSCAPE;
//        } else {
//            return Orientation.PORTRAIT;
//        }
//    }
//
//    public static String encodeByteArrayToString(byte[] image) {
//        return Base64.encodeBase64String(image);
//    }
//
//    public static String encodeByteArrayToMd5(byte[] image) {
//        return DigestUtils.md5Hex(image);
//    }
//
//    public static String encodeBase64StringToMd5(String base64String) {
//        return DigestUtils.md5Hex(Base64.decodeBase64(base64String));
//    }

}
