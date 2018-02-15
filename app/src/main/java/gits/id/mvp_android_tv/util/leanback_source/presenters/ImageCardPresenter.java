package gits.id.mvp_android_tv.util.leanback_source.presenters;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import gits.id.mvp_android_tv.util.leanback_source.models.Card;
import gits.id.mvp_android_tv.R;


/**
 * @author radhikayusuf.
 */

public class ImageCardPresenter extends AbstractCardPresenter<ImageCardView> {

    public ImageCardPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public ImageCardPresenter(Context context) {
        this(context, R.style.MovieCardTheme);
    }

    @Override
    protected ImageCardView onCreateView() {
        ImageCardView imageCardView = new ImageCardView(getContext());
        return imageCardView;
    }

    @Override
    public void onBindViewHolder(Card card, final ImageCardView cardView) {
        cardView.setTag(card);
        cardView.setTitleText(card.getTitle());
        cardView.setContentText(card.getDescription());

        cardView.getMainImageView().setMinimumHeight(card.getHeight());
        cardView.getMainImageView().setMaxHeight(card.getHeight());

        cardView.getMainImageView().setMinimumWidth(card.getWidth());
        cardView.getMainImageView().setMaxWidth(card.getWidth());

        Glide.with(getContext())
                .load(card.getImageUrl())
                .into(cardView.getMainImageView());
        cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);


    }

}
