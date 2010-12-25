class GroupsTable < ActiveRecord::Migration
  def self.up
    create_table :groups, :force => true do |t|
      t.string :username, :null => false
      t.string :groupname, :null => false
    end
    add_index :groups, :groupname, :unique => true
  end

  def self.down
    raise ActiveRecord::IrreversibleMigration
  end
end
