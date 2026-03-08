import { useEffect, useState } from "react";
import { getOrders } from "../services/ordersService";

const OrdersPage = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    loadOrders();
  }, []);

  const loadOrders = async () => {
    const data = await getOrders();
    setOrders(data);
  };

  return (
    <div>
      <h1>Mis pedidos</h1>

      {orders.length === 0 ? (
        <p>No hay ningun pedido.</p>
      ) : (
        orders.map((order) => (
          <div key={order.id}>
            <p>Pedido #{order.id}</p>
            <p>Producto ID: {order.productId}</p>
            <p>Estado: {order.status}</p>
          </div>
        ))
      )}
    </div>
  );
};

export default OrdersPage;