
// Backend: Node.js + Express + MongoDB
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

mongoose.connect('mongodb://localhost:27017/skillswap', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const userSchema = new mongoose.Schema({
  name: String,
  skillOffered: String,
  skillWanted: String,
});

const User = mongoose.model('User', userSchema);

app.get('/api/users', async (req, res) => {
  const users = await User.find();
  res.json(users);
});

app.post('/api/users', async (req, res) => {
  const { name, skillOffered, skillWanted } = req.body;
  const newUser = new User({ name, skillOffered, skillWanted });
  await newUser.save();
  res.status(201).json(newUser);
});

const PORT = 5000;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
