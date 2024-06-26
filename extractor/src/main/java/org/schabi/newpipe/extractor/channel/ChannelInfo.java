package org.schabi.newpipe.extractor.channel;

import org.schabi.newpipe.extractor.ListExtractor.InfoItemsPage;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.Page;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.ListLinkHandler;
import org.schabi.newpipe.extractor.stream.StreamInfoItem;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

import java.io.IOException;

/*
 * Created by Christian Schabesberger on 31.07.16.
 *
 * Copyright (C) 2016 Christian Schabesberger <chris.schabesberger@mailbox.org>
 * ChannelInfo.java is part of NewPipe Extractor.
 *
 * NewPipe Extractor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NewPipe Extractor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NewPipe Extractor.  If not, see <http://www.gnu.org/licenses/>.
 */

public class ChannelInfo extends ListInfo<StreamInfoItem> {

    public ChannelInfo(final int serviceId,
                       final String id,
                       final String url,
                       final String originalUrl,
                       final String name,
                       final ListLinkHandler listLinkHandler) {
        super(serviceId, id, url, originalUrl, name, listLinkHandler.getContentFilters(),
                listLinkHandler.getSortFilter());
    }

    public static ChannelInfo getInfo(final String url) throws IOException, ExtractionException {
        return getInfo(NewPipe.getServiceByUrl(url), url);
    }

    public static ChannelInfo getInfo(final StreamingService service, final String url)
            throws IOException, ExtractionException {
        final ChannelExtractor extractor = service.getChannelExtractor(url);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static InfoItemsPage<StreamInfoItem> getMoreItems(final StreamingService service,
                                                             final String url,
                                                             final Page page)
            throws IOException, ExtractionException {
        return service.getChannelExtractor(url).getPage(page);
    }

    public static ChannelInfo getInfo(final ChannelExtractor extractor)
            throws IOException, ExtractionException {

        final int serviceId = extractor.getServiceId();
        final String id = extractor.getId();
        final String url = extractor.getUrl();
        final String originalUrl = extractor.getOriginalUrl();
        final String name = extractor.getName();

        final ChannelInfo info =
                new ChannelInfo(serviceId, id, url, originalUrl, name, extractor.getLinkHandler());

        try {
            info.setAvatarUrl(extractor.getAvatarUrl());
        } catch (final Exception e) {
            info.addError(e);
        }
        try {
            info.setBannerUrl(extractor.getBannerUrl());
        } catch (final Exception e) {
            info.addError(e);
        }
        try {
            info.setFeedUrl(extractor.getFeedUrl());
        } catch (final Exception e) {
            info.addError(e);
        }

        final InfoItemsPage<StreamInfoItem> itemsPage =
                ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(itemsPage.getItems());
        info.setNextPage(itemsPage.getNextPage());

        try {
            info.setSubscriberCount(extractor.getSubscriberCount());
        } catch (final Exception e) {
            info.addError(e);
        }
        try {
            info.setDescription(extractor.getDescription());
        } catch (final Exception e) {
            info.addError(e);
        }

        try {
            info.setParentChannelName(extractor.getParentChannelName());
        } catch (final Exception e) {
            info.addError(e);
        }

        try {
            info.setParentChannelUrl(extractor.getParentChannelUrl());
        } catch (final Exception e) {
            info.addError(e);
        }

        try {
            info.setParentChannelAvatarUrl(extractor.getParentChannelAvatarUrl());
        } catch (final Exception e) {
            info.addError(e);
        }

        try {
            info.setVerified(extractor.isVerified());
        } catch (final Exception e) {
            info.addError(e);
        }

        return info;
    }

    private String avatarUrl;
    private String parentChannelName;
    private String parentChannelUrl;
    private String parentChannelAvatarUrl;
    private String bannerUrl;
    private String feedUrl;
    private long subscriberCount = -1;
    private String description;
    private String[] donationLinks;
    private boolean verified;

    public String getParentChannelName() {
        return parentChannelName;
    }

    public void setParentChannelName(final String parentChannelName) {
        this.parentChannelName = parentChannelName;
    }

    public String getParentChannelUrl() {
        return parentChannelUrl;
    }

    public void setParentChannelUrl(final String parentChannelUrl) {
        this.parentChannelUrl = parentChannelUrl;
    }

    public String getParentChannelAvatarUrl() {
        return parentChannelAvatarUrl;
    }

    public void setParentChannelAvatarUrl(final String parentChannelAvatarUrl) {
        this.parentChannelAvatarUrl = parentChannelAvatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(final String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(final String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public long getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(final long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String[] getDonationLinks() {
        return donationLinks;
    }

    public void setDonationLinks(final String[] donationLinks) {
        this.donationLinks = donationLinks;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(final boolean verified) {
        this.verified = verified;
    }
}
