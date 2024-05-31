const orders = new Map([
	[
		"34a5521e-e24e-4ae7-9e59-20e2d564f52c",
		{
			id: "34a5521e-e24e-4ae7-9e59-20e2d564f52c",
			userId: "a4d49a45-da62-4bef-8712-01f8c1846cd5",
			name: "Coca Cola",
			price: 30.99,
			quantity: 1,
		},
	],
	[
		"f4d5b1a1-5f3e-4c3e-8f1d-6e9b6c5e9e4d",
		{
			id: "f4d5b1a1-5f3e-4c3e-8f1d-6e9b6c5e9e4d",
			userId: "a4d49a45-da62-4bef-8712-01f8c1846cd5",
			name: "Pepsi",
			price: 25.99,
			quantity: 2,
		},
	],
	[
		"e3a5f1a1-5f3e-4c3e-8f1d-6e9b6c5e9e4d",
		{
			id: "e3a5f1a1-5f3e-4c3e-8f1d-6e9b6c5e9e4d",
			userId: "42a6bf7a-e98f-4d1e-99ae-ccda1577f9ad",
			name: "Fanta",
			price: 20.99,
			quantity: 3,
		},
	],
]);

const orderFields = Object.keys(orders.values().next().value);

module.exports = {
	async create(user) {
		const keys = Object.keys(user);
		if (orderFields.some((field) => !keys.includes(field))) {
			throw new Error("Missing fields");
		}

		if (await this.findById(user.sub)) {
			throw new Error("Order already exists");
		}

		orders.set(user.sub, user);
	},

	async findById(sub) {
		return orders.get(sub) ?? null;
	},
};
