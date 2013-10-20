package com.mrshalite.whereismymoney;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ExpendContract {

	public static final String AUTHORITY = "com.mrsahlite.whereismymoney.provider.expend";
	
	// this class can not be instantiated
	private ExpendContract(){}
	
	public static final class Expend implements BaseColumns{
		
		// this class can not be instantiated
		private Expend(){}
		
		 /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "expends";

        /*
         * URI definitions
         */

        /**
         * The scheme part for this provider's URI
         */
        private static final String SCHEME = "content://";

        /**
         * Path parts for the URIs
         */

        /**
         * Path part for the expends URI
         */
        public static final String PATH_EXPENDS = "/expends";

        /**
         * Path part for the expend ID URI
         */
        public static final String PATH_EXPEND_ID = "/expends/";

        /**
         * 0-relative position of a note ID segment in the path part of a expend ID URI
         */
        public static final int EXPEND_ID_PATH_POSITION = 1;
        
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_EXPENDS);

        /**
         * The content URI base for a single expend. Callers must
         * append a numeric expend id to this Uri to retrieve a note
         */
        public static final Uri CONTENT_ID_URI_BASE
            = Uri.parse(SCHEME + AUTHORITY + PATH_EXPEND_ID);

        /**
         * The content URI match pattern for a single expend, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent.
         */
        public static final Uri CONTENT_ID_URI_PATTERN
            = Uri.parse(SCHEME + AUTHORITY + PATH_EXPEND_ID + "/#");
        
        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of expends.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mrsahlite.expend";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single expend.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.mrsahlite.expend";
        
        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "modified DESC";

        /*
         * Column definitions
         */

        /**
         * Column name for the year of expend item
         * <P>Type: INTEGER</P>
         */
        public static final String COLUMN_NAME_YEAR = "year";
        
        /**
         * Column name for the month of expend item
         * <P>Type: INTEGER</P>
         */
        public static final String COLUMN_NAME_MONTH = "month";
        
        /**
         * Column name for the date of expend item
         * <P>Type: INTEGER</P>
         */
        public static final String COLUMN_NAME_DATE = "date";
        
        /**
         * Column name for the name of expend item
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_EXPEND = "name";
        
        /**
         * Column name for the money of expend item
         * <P>Type: REAL</P>
         */
        public static final String COLUMN_NAME_MONEY = "money";
        
        /**
         * Column name for the type1 of expend item
         * <P>Type: INTEGER </P>
         */
        public static final String COLUMN_NAME_TYPE1 = "type1";
        
        /**
         * Column name for the type2 of expend item
         * <P>Type: INTEGER </P>
         */
        public static final String COLUMN_NAME_TYPE2 = "type2";
        
        /**
         * Column name for the type3 of expend item
         * <P>Type: INTEGER </P>
         */
        public static final String COLUMN_NAME_TYPE3 = "type3";
        
        /**
         * Column name for the type4 of expend item
         * <P>Type: INTEGER </P>
         */
        public static final String COLUMN_NAME_TYPE4 = "type4";
	}
}
