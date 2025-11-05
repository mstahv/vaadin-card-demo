package com.example.examplefeature.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.markdown.Markdown;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;

@Route("/")
@PageTitle("Shipment card examples")
@Menu(order = 0, icon = "vaadin:truck", title = "Shipment Cards")
class ShipmentCardView extends Main {

    // Reusable content constants
    private static final String IMAGE_URL = "https://images.unsplash.com/photo-1601584115197-04ecc0da31d7?w=800&auto=format&fit=crop&q=60";
    private static final String IMAGE_ALT = "Freight truck on highway";
    private static final String TITLE_TEXT = "Shipment #VDN-45892";
    private static final String SUBTITLE_TEXT = "Berlin, DE → Turku, FI";
    private static final String BADGE_TEXT = "In Transit";
    private static final String CARRIER_TEXT = "Carrier: Goodie Transport GmbH";
    private static final String SHIPMENT_TEXT = "Berlin → Travemünde (ferry) → Naantali → Turku. 12 pallets of temperature-controlled goods.";
    private static final String PRIMARY_BUTTON_TEXT = "Update Status";
    private static final String SECONDARY_BUTTON_TEXT = "Track Shipment";

    private final List<Card> cards = new ArrayList<>();
    private int currentCardIndex = 0;
    private final Div cardContainer;
    private final Button prevButton;
    private final Button nextButton;
    private final Span cardCounter;

    ShipmentCardView() {
        addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.FlexDirection.COLUMN,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.Padding.LARGE
        );
        
        getStyle().set("min-height", "100vh");

        // Initialize all cards
        cards.add(createCard11());
        cards.add(createCard10());
        cards.add(createCard9());
        cards.add(createCard8());
        cards.add(createCard7());
        cards.add(createCard6());
        cards.add(createCard5());
        cards.add(createCard4());
        cards.add(createCard3());
        cards.add(createCard2());
        cards.add(createCard1());
        cards.add(createCard0());

        // Create previous button with keyboard shortcut
        prevButton = new Button(VaadinIcon.ARROW_LEFT.create());
        prevButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_LARGE);
        prevButton.addClickListener(e -> showPreviousCard());
        prevButton.addClickShortcut(Key.ARROW_LEFT);
        prevButton.setAriaLabel("Previous card");

        // Create next button with keyboard shortcut
        nextButton = new Button(VaadinIcon.ARROW_RIGHT.create());
        nextButton.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_LARGE);
        nextButton.addClickListener(e -> showNextCard());
        nextButton.addClickShortcut(Key.ARROW_RIGHT);
        nextButton.setAriaLabel("Next card");

        // Add card counter
        cardCounter = new Span();
        cardCounter.addClassNames(
                LumoUtility.Margin.Bottom.MEDIUM,
                LumoUtility.FontSize.XLARGE,
                LumoUtility.FontWeight.MEDIUM
        );

        cardContainer = new Div();
        cardContainer.addClassNames(
                LumoUtility.Display.FLEX,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.JustifyContent.CENTER
        );
        cardContainer.getStyle()
                .set("min-height", "500px")
                .set("width", "100%");

        // Create a vertical layout for counter and card
        VerticalLayout cardSection = new VerticalLayout(cardCounter, cardContainer);
        cardSection.setSpacing(false);
        cardSection.setPadding(false);
        cardSection.setAlignItems(VerticalLayout.Alignment.CENTER);
        cardSection.addClassNames(LumoUtility.Gap.MEDIUM);
        cardSection.setWidth("100%");

        // Create navigation layout
        HorizontalLayout navigation = new HorizontalLayout(prevButton, cardSection, nextButton);
        navigation.setAlignItems(HorizontalLayout.Alignment.START);
        navigation.addClassNames(LumoUtility.Gap.MEDIUM, LumoUtility.Margin.Top.LARGE);
        navigation.setWidthFull();
        navigation.getStyle()
                .set("max-width", "900px")
                .set("flex-shrink", "0");

        add(navigation);

        currentCardIndex = cards.size() -1;
        // Show first card
        updateCardDisplay();
    }

    private void showPreviousCard() {
        if (currentCardIndex > 0) {
            currentCardIndex--;
            updateCardDisplay();
        }
    }

    private void showNextCard() {
        if (currentCardIndex < cards.size() - 1) {
            currentCardIndex++;
            updateCardDisplay();
        }
    }

    private void updateCardDisplay() {
        cardContainer.removeAll();
        cardContainer.add(cards.get(currentCardIndex));
        
        // Update button states
        prevButton.setEnabled(currentCardIndex > 0);
        nextButton.setEnabled(currentCardIndex < cards.size() - 1);
        
        // Update counter
        cardCounter.setText(String.format("Card %d of %d", currentCardIndex + 1, cards.size()));
    }

    private Card createCard0() {
        Card card = new ShipmentCard(new ShipmentDto(
                "VDN-45892",
                "Berlin, DE → Turku, FI",
                """
                Carrier: Goodie Transport GmbH
                
                Berlin → Travemünde (ferry) → Naantali → Turku. 12 pallets of temperature-controlled goods.
                """,
                TransitStatus.InTransit,
                "Goodie Transporter",
                IMAGE_URL
        ));
        return card;
    }
    
    private Card createCard1() {
        Card card = new Card();
        card.addThemeVariants(
            CardVariant.LUMO_COVER_MEDIA);

        card.setWidth("400px");

        card.setMedia(new Image(IMAGE_URL, IMAGE_ALT));
        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));

        Avatar avatar = new Avatar("Goodie Transport");
        avatar.setColorIndex(2);
        card.setHeaderPrefix(avatar);
        
        Span badge = new Span(BADGE_TEXT);
        badge.getElement().getThemeList().add("badge success");
        card.setHeaderSuffix(badge);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard2() {
        Card card = new Card();
        card.addThemeVariants(
            CardVariant.LUMO_COVER_MEDIA);

        card.setWidth("400px");

        card.setMedia(new Image(IMAGE_URL, IMAGE_ALT));
        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));
        
        Span badge = new Span(BADGE_TEXT);
        badge.getElement().getThemeList().add("badge success");
        card.setHeaderSuffix(badge);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard3() {
        Card card = new Card();

        card.setWidth("400px");

        card.setMedia(new Image(IMAGE_URL, IMAGE_ALT));
        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));
        
        Span badge = new Span(BADGE_TEXT);
        badge.getElement().getThemeList().add("badge success");
        card.setHeaderSuffix(badge);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard4() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));
        
        Span badge = new Span(BADGE_TEXT);
        badge.getElement().getThemeList().add("badge success");
        card.setHeaderSuffix(badge);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }
  
    private Card createCard5() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));
        
        Span badge = new Span(BADGE_TEXT);
        card.setHeaderSuffix(badge);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard6() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
        card.setSubtitle(new Span(SUBTITLE_TEXT));
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard7() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn, new Button(SECONDARY_BUTTON_TEXT));

        return card;
    }

    private Card createCard8() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        primaryBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        card.addToFooter(primaryBtn);

        return card;
    }

    private Card createCard9() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));
        
        Button primaryBtn = new Button(PRIMARY_BUTTON_TEXT);
        card.addToFooter(primaryBtn);

        return card;
    }

    private Card createCard10() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);
       
        card.add(new Paragraph(CARRIER_TEXT),
            new Paragraph(SHIPMENT_TEXT));

        return card;
    }

    private Card createCard11() {
        Card card = new Card();

        card.setWidth("400px");

        card.setTitle(TITLE_TEXT);

        card.add(new Paragraph(CARRIER_TEXT));

        return card;
    }















    class ShipmentCard extends Card {
        public ShipmentCard(ShipmentDto dto) {
            setTitle("Shipment #" + dto.id());
            //setSubtitle(dto.route()); // Use this in example instead of the next line, even though it dont' compile yet PR not yet released.️
            setSubtitle(new Span(dto.route()));
            add(new Markdown(dto.shipmentDescription()));

            setMedia(new Image(dto.truckImageUrl(), "Truck image"));
            setHeaderPrefix(new DriverAvatar(dto.driver()));
            setHeaderSuffix(createStatusBadge(dto.status()));

            addToFooter(
                    new DefaultButton("Update Status", evt -> openShipmentForm(dto)),
                    new Button("Track shipement", evt -> viewOnMap(dto))
            );

            addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
            setWidth("400px");

        }

        private void openShipmentForm(ShipmentDto shipment) {
        }

        private Component createStatusBadge(TransitStatus status) {
            // hard coded demo...
            return new InTransitBadge();
        }

        private void viewOnMap(ShipmentDto shipment) {
        }

        static class InTransitBadge extends Span {
            public InTransitBadge() {
                super(BADGE_TEXT);
                getElement().getThemeList().add("badge success");
            }
        }


        class DefaultButton extends Button {
            public DefaultButton(ShipmentDto shipment) {
                super(PRIMARY_BUTTON_TEXT);
                addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            }

            public DefaultButton(String updateStatus, ComponentEventListener<ClickEvent<Button>> listener) {
                super(updateStatus, listener);
            }
        }

        private class DriverAvatar extends Avatar {
            public DriverAvatar(String driverName) {
                super(driverName);
                setColorIndex(2);
            }
        }
    }
}
