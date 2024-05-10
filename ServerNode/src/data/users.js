const bcrypt = require("bcrypt");

module.exports = {
	users: new Map([
		[
			"834d928f-08cd-406d-a472-f32d5e5556b1",
			{
				sub: "834d928f-08cd-406d-a472-f32d5e5556b1",
				username: "admin",
				password: bcrypt.hashSync("password", 10),
				role: "admin"
			},
		],
		[
			"a4d49a45-da62-4bef-8712-01f8c1846cd5",
			{
				sub: "a4d49a45-da62-4bef-8712-01f8c1846cd5",
				username: "rodrigo",
				password: bcrypt.hashSync("password", 10),
				role: "user"
			},
		],
		[
			"42a6bf7a-e98f-4d1e-99ae-ccda1577f9ad",
			{
				sub: "42a6bf7a-e98f-4d1e-99ae-ccda1577f9ad",
				username: "goncalo",
				password: bcrypt.hashSync("password", 10),
				role: "user"
			},
		],
		[
			"40dd8ae4-e76c-4480-83fd-ba699377401d",
			{
				sub: "40dd8ae4-e76c-4480-83fd-ba699377401d",
				username: "joao",
				password: bcrypt.hashSync("password", 10),
				role: "user"
			},
		],
		[
			"20e689aa-b396-4efe-9674-8a0b0a895703",
			{
				sub: "20e689aa-b396-4efe-9674-8a0b0a895703",
				username: "leandro",
				password: bcrypt.hashSync("password", 10),
				role: "user"
			},
		],
	])
};
