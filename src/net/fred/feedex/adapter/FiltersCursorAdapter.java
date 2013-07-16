/**
 * FeedEx
 * 
 * Copyright (c) 2012-2013 Frederic Julian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.fred.feedex.adapter;

import net.fred.feedex.R;
import net.fred.feedex.provider.FeedData.FilterColumns;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class FiltersCursorAdapter extends ResourceCursorAdapter {

	private int filterTextColumnPosition;
	private int isAppliedToTitleColumnPosition;

	private int mSelectedFilter = -1;

	public FiltersCursorAdapter(Context context, Cursor cursor) {
		super(context, android.R.layout.simple_list_item_2, cursor, 0);

		reinit(cursor);
	}

	@Override
	public void bindView(View view, final Context context, Cursor cursor) {
		TextView filterTextTextView = (TextView) view.findViewById(android.R.id.text1);
		TextView isAppliedToTitleTextView = (TextView) view.findViewById(android.R.id.text2);

		if (cursor.getPosition() == mSelectedFilter) {
			view.setBackgroundResource(android.R.color.holo_blue_dark);
		} else {
			view.setBackgroundResource(android.R.color.transparent);
		}

		filterTextTextView.setText(cursor.getString(filterTextColumnPosition));
		isAppliedToTitleTextView.setText(cursor.getInt(isAppliedToTitleColumnPosition) == 1 ? R.string.filter_apply_to_title : R.string.filter_apply_to_content);
	}

	@Override
	public void changeCursor(Cursor cursor) {
		reinit(cursor);
		super.changeCursor(cursor);
	}

	@Override
	public Cursor swapCursor(Cursor newCursor) {
		reinit(newCursor);
		return super.swapCursor(newCursor);
	}

	@Override
	public void notifyDataSetChanged() {
		reinit(null);
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		reinit(null);
		super.notifyDataSetInvalidated();
	}

	private void reinit(Cursor cursor) {
		if (cursor != null) {
			filterTextColumnPosition = cursor.getColumnIndex(FilterColumns.FILTER_TEXT);
			isAppliedToTitleColumnPosition = cursor.getColumnIndex(FilterColumns.IS_APPLIED_TO_TITLE);
		}
	}

	public void setSelectedFilter(int filterPos) {
		mSelectedFilter = filterPos;
	}

	public int getSelectedFilter() {
		return mSelectedFilter;
	}
}
