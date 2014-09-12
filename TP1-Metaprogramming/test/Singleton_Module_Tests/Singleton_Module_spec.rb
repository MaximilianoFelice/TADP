require 'rspec'
require "TP1/Prototyped"
require "TP1/Metaprogramming"
require "TP1/Singleton_Module_Behaviour"

describe 'Extend with Singleton Modules' do

  before(:all) do
    @previouslyProto = TP::PrototypedObject.new
    @newObject = TP::PrototypedObject.new
    @latelyProto = TP::PrototypedObject.new
  end

  it 'Should add Singleton Module Behaviour to every instance' do

    #TODO: NOT IMPLEMENTED
  end

  it 'Should let Singleton Modules find behaviour' do

    @previouslyProto.set_prototype(@newObject)

    @newObject.set_method(:a, lambda{50})
    expect(@newObject.singleton_module.method_defined?(:a)).to eq(true)

    @latelyProto.set_prototype(@newObject)

    expect(@previouslyProto.a).to eq(50)
    expect(@latelyProto.a).to eq(50)
  end

end