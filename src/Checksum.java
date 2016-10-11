//package niklasf.datakom.chatServer.pdu;

/**
 * Checksum.java
 *
 * <p>
 *
 * Given file for assignment 2 - Distributed Chat on Computer Communication
 * and Networks C, 5p at Ume&aring; University fall 2001 and spring 2002
 * By Per Nordlinder (per@cs.umu.se) and Jon Hollstr&ouml;m (jon@cs.umu.se)
 *
 * <p>
 *
 * Modified by Niklas Fries fall 2015: removed unnecessary arguments,
 * refactoring, documentation.
 */

/**
 * Utility class with method for computing checksums over byte arrays using
 * ones' complement sum.
 *
 * <p>
 *
 * To assign the checksum to a message, use the following algorithm:
 *
 * <ol>
 *     <li>Assign 0 to the position where the checksum should be written.</li>
 *     <li>Compute the checksum over the entire message.</li>
 *     <li>Assign the result to the position for the checksum.</li>
 * </ol>
 *
 * To check whether a message has a correct checksum, compute the checksum
 * of the entire message including the position where the checksum is supposed
 * to be and assert that the result is -1 on two's complement form.
 */
public class Checksum {

    /**
     * @param buf The buffer to calculate the checksum for.
     * @return The checksum. Should be -1 when checking whether a PDU has
     * correct checksum.
     */

    //// TODO: 2016-10-11 test the checksum, not sure about it returning -1
    public static byte computeChecksum(byte[] buf) {

        int sum = 0;

        for (int i = 0; i < buf.length; i++) {

            sum += buf[i];

            if(sum > 255){
                sum = sum - 255;
            }
        }
        return (byte)(255 - sum);
    }
}
