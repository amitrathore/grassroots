class UsersTable < ActiveRecord::Migration
  def self.up
    create_table :users, :force => true do |t|
      t.string :first_name
      t.string :last_name
      t.string :login
      t.string :email, :null => false
      t.string :encrypted_password
      t.string :salt
    end
    add_index :users, :email, :unique => true
  end

  def self.down
    drop_table :users
  end
end
