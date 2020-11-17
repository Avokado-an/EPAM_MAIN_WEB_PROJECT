package com.anton.gym.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code PaginationIndexesDefiner} class represents PaginationIndexesDefiner.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class PaginationIndexesDefiner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int STARTING_PAGE_INDEX = 1;
    private static final int MAX_USERS_ON_PAGE = 15;

    /**
     * define max amount of pages
     * @param usersSize the amount of users list
     * @return the amount of pages
     */
    public static int defineMaxPagesAmount(int usersSize) {
        int maxPagesAmount = usersSize / MAX_USERS_ON_PAGE;
        if (usersSize % MAX_USERS_ON_PAGE != 0) {
            maxPagesAmount++;
        }
        return maxPagesAmount;
    }

    /**
     * define starting index of users on page
     * @param pageNumber the number of the page
     * @return starting index
     */
    public static int defineMinUserIndex(int pageNumber) {
        return pageNumber * MAX_USERS_ON_PAGE - MAX_USERS_ON_PAGE;
    }

    /**
     * define last index of users on page
     * @param usersSize the size of users list
     * @param minIndex the min index
     * @return ending index
     */
    public static int defineMaxUserIndex(int usersSize, int minIndex) {
        int maxIndex = minIndex + MAX_USERS_ON_PAGE - 1;
        if (maxIndex >= usersSize) {
            maxIndex = usersSize - 1;
        }
        return maxIndex;
    }

    /**
     * define current page index
     * @param currentPageNumber the number of the current page
     * @return page index
     */
    public static int definePageIndex(String currentPageNumber) {
        int index = STARTING_PAGE_INDEX;
        try {
            if (currentPageNumber != null) {
                if (!currentPageNumber.isEmpty()) {
                    index = Integer.parseInt(currentPageNumber);
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.debug("Page number is not int value for some reason");
        }
        return index;
    }
}
