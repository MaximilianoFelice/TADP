require 'rspec'
require "TP1/Metaprogramming"

describe 'Extend with Singleton Modules' do

  before(:all) do
    @anyObject = Object.new
    @anyObject.extend TP::Singleton_Module
  end

  it 'Should let Singleton Modules add behaviour' do

    @anyObject.define_singleton_module_method(:a, lambda{50})

    expect(@anyObject.a).to eq(50)

  end

end