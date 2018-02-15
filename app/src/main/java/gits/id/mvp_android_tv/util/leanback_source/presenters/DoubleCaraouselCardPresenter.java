package gits.id.mvp_android_tv.util.leanback_source.presenters;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import gits.id.mvp_android_tv.R;
import gits.id.mvp_android_tv.util.leanback_source.models.Card;


/**
 * @author radhikayusuf.
 */

public class DoubleCaraouselCardPresenter extends AbstractCardPresenter<ImageCardView> {

    public DoubleCaraouselCardPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public DoubleCaraouselCardPresenter(Context context) {
        this(context, R.style.DoubleCardTheme);
//        super(context);
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


        if (card.getWidth() != 0 && card.getHeight() != 0) {
            cardView.getMainImageView().setMaxWidth(card.getWidth());
            cardView.getMainImageView().setMinimumWidth(card.getWidth());

            cardView.getMainImageView().setMaxHeight(card.getHeight());
            cardView.getMainImageView().setMinimumHeight(card.getHeight());

        } else {
            cardView.getMainImageView().setMaxWidth(cardView.getResources().getDimensionPixelSize(R.dimen.double_image_card_width));
            cardView.getMainImageView().setMinimumWidth(cardView.getResources().getDimensionPixelSize(R.dimen.double_image_card_width));

            cardView.getMainImageView().setMaxHeight(cardView.getResources().getDimensionPixelSize(R.dimen.double_image_card_height));
            cardView.getMainImageView().setMinimumHeight(cardView.getResources().getDimensionPixelSize(R.dimen.double_image_card_height));
        }

        Glide.with(getContext())
                .load(card.getImageUrl())
                .into(cardView.getMainImageView());
        cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);

    }

}
