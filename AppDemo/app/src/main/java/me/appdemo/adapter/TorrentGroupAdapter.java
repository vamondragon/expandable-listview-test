package me.appdemo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import me.appdemo.DataModel.Artist;
import me.appdemo.DataModel.Torrents;
import me.appdemo.R;

/**
 * Displays a list of the torrents in the group for selection
 */
public class TorrentGroupAdapter extends BaseExpandableListAdapter {
    private final LayoutInflater inflater;

    /**
     * The artists who appeared on this release and the string for the artist header
     * this header text is Additional Artists if some artists are being shown in the title
     * header, or is simply Artists on a various artists release
     */
    private List<Artist> artists;
    private String artistHeader;

    /**
     * The list of editions being displayed
     */
    private List<Torrents> editions;
    private String editionsHeader;

    /**
     * Setup the adapter to display a list of torrents for some group and the passed list of artists
     */
    public TorrentGroupAdapter(Context context, List<Artist> a, List<Torrents> objects) {
        super();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        artists = a;
        editions = objects;
        artistHeader = "Additional Artists";
    }

    /**
     * Type 0 is artist view, 1 is torrent
     */
    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return groupPosition == 0 && artists != null && !artists.isEmpty() ? 0 : 1;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (artists != null && !artists.isEmpty()) {
            if (groupPosition == 0) {
                return artists.size();
            }
        }
        return editions.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if (artists != null && !artists.isEmpty()) {
            if (groupPosition == 0) {
                return artists.get(childPosition);
            }
        }
        return editions.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (getChildType(groupPosition, childPosition) == 0) {
            return getArtistView(childPosition, isLastChild, convertView, parent);
        }
        return getTorrentView(groupPosition, childPosition, isLastChild, convertView, parent);
    }

    /**
     * Build the artist name view to return a view showing the name of one of the contributing artists
     */
    private View getArtistView(int childpos, boolean isLastChild, View convertView, ViewGroup parent) {
        ArtistViewHolder holder = null;
        if (convertView != null) {
            try {
                holder = (ArtistViewHolder) convertView.getTag();
            } catch (ClassCastException e) {
                convertView = null;
            }
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_torrent_artist, parent, false);
            holder = new ArtistViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.artist_name);
            holder.type = (TextView) convertView.findViewById(R.id.artist_type);
            convertView.setTag(holder);
        }
        holder.artist = artists.get(childpos);
        holder.name.setText(holder.artist.getName());
        holder.type.setText("XXXXXXXXX");
        return convertView;
    }

    /**
     * Build the torrent view to return a view showing information about one of the torrents available to download
     */
    private View getTorrentView(int grouppos, int childpos, boolean isLastChild, View convertView, ViewGroup parent) {
        TorrentViewHolder holder = null;
        if (convertView != null) {
            try {
                holder = (TorrentViewHolder) convertView.getTag();
            } catch (ClassCastException e) {
                convertView = null;
            }
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_group_torrent, parent, false);
            holder = new TorrentViewHolder();
            holder.format = (TextView) convertView.findViewById(R.id.format);
            holder.size = (TextView) convertView.findViewById(R.id.size);
            holder.snatches = (TextView) convertView.findViewById(R.id.snatches);
            holder.seeders = (TextView) convertView.findViewById(R.id.seeders);
            holder.leechers = (TextView) convertView.findViewById(R.id.leechers);
            holder.freeleech = convertView.findViewById(R.id.freeleech_icon);
            holder.reported = convertView.findViewById(R.id.reported_icon);
            convertView.setTag(holder);
        }

        holder.torrent = (Torrents) getChild(grouppos, childpos);
        holder.format.setText("XXXXX");
        holder.snatches.setText("AKAKAKAKAKAAKAKKa");
        holder.seeders.setText("SIDIDIDIDIDIDIDDID");
        holder.leechers.setText("IDIDIDIDIDIDIDIDIDIDIDDIDIDID");


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupCount() {
        return artists != null && !artists.isEmpty() ? 1 + editions.size() : editions.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (artists != null && !artists.isEmpty()) {
            return groupPosition == 0 ? artists : editions.get(groupPosition - 1);
        }
        return editions.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;

        if (convertView != null) {
            holder = (GroupViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.list_group, parent, false);
            holder = new GroupViewHolder();
            holder.groupName = (TextView) convertView.findViewById(R.id.group_category);
            convertView.setTag(holder);
        }

        if (artists != null && !artists.isEmpty()) {
            if (groupPosition == 0) {
                holder.groupName.setText(artistHeader);
            } else {
                holder.groupName.setText("ALbunes MDFK");
            }
        } else {
            holder.groupName.setText("");
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * View holder for the group information
     */
    private static class GroupViewHolder {
        public TextView groupName;
    }

    /**
     * View holder for the Torrent information
     */
    private static class TorrentViewHolder {
        public TextView format, size, snatches, seeders, leechers;
        public View freeleech, reported;
        public Torrents torrent;
    }

    /**
     * View holder for the Artist information
     */
    private static class ArtistViewHolder {
        public TextView name, type;
        public Artist artist;
    }
}
