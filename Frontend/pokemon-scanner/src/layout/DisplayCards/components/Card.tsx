import type CardModel from "../../../models/CardModel";

export const Card: React.FC<{ card: CardModel }> = (props) => {
    return (
        <div className="card shadow-sm" style={{ width: '200px' }}>
            <img
                src={props.card.img}
                className="card-img-top"
                alt={props.card.cardName}
                style={{ height: '150px', objectFit: 'cover' }}
            />

            <div className="card-body p-2">
                <h6 className="card-title mb-1">{props.card.cardName}</h6>
                <p className="card-text text-muted mb-1" style={{ fontSize: '0.85rem' }}>
                    {props.card.setName}
                </p>
                <p className="fw-bold mb-0">${props.card.price.toFixed(2)}</p>
            </div>
        </div>
    );
};