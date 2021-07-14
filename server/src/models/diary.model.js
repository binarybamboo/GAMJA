const mongoose = require('mongoose');

const diarySchema = mongoose.Schema(
  {
    name: {
      type: String,
      required: false,
    },
    description: {
      type: String,
      required: true,
    },
    photo: [{ type: String }],
    author: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  },
  {
    timestamps: true,
  },
);

const DiaryGroup = mongoose.model('Diary', diarySchema);

module.exports = DiaryGroup;
