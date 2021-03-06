package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.logging.Logger;

@FXMLController
public class UserDetailsLayoutController implements Initializable {

    public static final Logger LOGGER = Logger.getLogger(UserDetailsLayoutController.class.getName());
    @FXML
    public Tab manageAccountTab;
    public Tab watchVideosTab;
    public Tab uploadVideoTab;
    public Tab searchVideoTab;
    public Tab categoriesTab;
    public Tab inProgressCourseViewTab;
    public Tab toWatchTab;
    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>(this, "user");

    public final SimpleObjectProperty<User> userProperty() {
        return user;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        user.addListener((observable, oldValue, newValue) -> {
            LOGGER.info("The user has changed to " + newValue.getUsername());
        });
    }

    public void loadTabContent() {
        try {
            manageAccountTab.setContent(getManageAccountLayout());
            uploadVideoTab.setContent(getUploadVideoLayout(getUser().getUserType()));
            watchVideosTab.setContent(getWatchVideoLayout());
            searchVideoTab.setContent(getSearchVideoTabLayout());
            categoriesTab.setContent(getCategoriesTabLayout());
            inProgressCourseViewTab.setContent(getInProgressTabLayout());
            toWatchTab.setContent(getToWatchTabLayout());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AnchorPane getManageAccountLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/ManageAccountLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ManageAccountLayoutController controller = fxmlLoader.getController();
        controller.getUsernameValue().setText(getUser().getUsername());
        return anchorPane;
    }

    private AnchorPane getUploadVideoLayout(UserType userType) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/UploadVideoLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        UploadVideoLayoutController controller = fxmlLoader.getController();
        if (userType == UserType.STUDENT) {
            controller.getMessageLabel().setText("NOT ALLOWED AS A STUDENT");

        } else {
            controller.getMessageLabel().setText("Here you can upload videos");
            controller.uploadFileButton.setVisible(true);
            controller.videoCategoryTextField.setVisible(true);
            controller.parentCategoryTextField.setVisible(true);
        }
        return anchorPane;
    }

    public User getUser() {
        return user.get();
    }

    private AnchorPane getWatchVideoLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/WatchVideosLayout.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        WatchVideosLayoutController controller = fxmlLoader.getController();
        //Fixme: Create generic URL
        controller.setRetriever(
                () -> UserDetailsLayoutController.this
                        .getVideos(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getVideosList"));
        controller.lateInitialize();
        return anchorPane;
    }

    private AnchorPane getSearchVideoTabLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/SearchVideoTabLayout.fxml"));
        return fxmlLoader.load();
    }

    private AnchorPane getCategoriesTabLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/CategoriesTabLayout.fxml"));
        return fxmlLoader.load();
    }

    private AnchorPane getInProgressTabLayout() throws IOException {
        return FXMLLoader.load(getClass().getResource("/layout/InProgressTabLayout.fxml"));
    }

    private AnchorPane getToWatchTabLayout() throws IOException {
        return FXMLLoader.load(getClass().getResource("/layout/ToWatchTabLayout.fxml"));
    }

    List<Video> getVideos(final String url) {
        try {
            final URLConnection urlConnection = getURLConnection(url);
            urlConnection.connect();
            final List<Video> videos = Arrays.asList(
                    Globals.GSON.fromJson(new InputStreamReader(urlConnection.getInputStream()), Video[].class));
            return videos;
        } catch (IOException e) {
            return null;
        }
    }

    private URLConnection getURLConnection(final String url) throws IOException {
        return new URL(url).openConnection();
    }

    public void setUser(final User user) {
        this.user.set(user);
    }
}
